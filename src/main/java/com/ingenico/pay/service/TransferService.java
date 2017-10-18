package com.ingenico.pay.service;

import com.ingenico.pay.entity.AccountEntity;
import com.ingenico.pay.exception.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by mohamedtantawy on 10/17/17.
 */
@Service
public class TransferService {

    @Autowired
    AccountService accountService;

    private static final Logger logger = Logger.getLogger(TransferService.class.getSimpleName());

    public void validateAccountAndDoTransfer(final String fromAccountId,final String toAccountId, final double amount) {

        accountService.validateAccount(fromAccountId);
        accountService.validateAccount(toAccountId);
        validateAmount(amount);

        AccountEntity fromAccountEntity = accountService.findEntity(fromAccountId);

        synchronized (fromAccountEntity) {
            try {
                accountService.checkAccountHasSufficientBalance(fromAccountId, amount);
                AccountEntity toAccountEntity = accountService.findEntity(toAccountId);

                synchronized (toAccountEntity) {

                    double fromAccountBalance = fromAccountEntity.getBalance();
                    double toAccountBalance = toAccountEntity.getBalance();

                    double newFromAccountBalance = fromAccountEntity.getBalance() - amount;
                    double newToAccountBalance = toAccountEntity.getBalance() +amount;

                    try {
                        fromAccountEntity.setBalance(newFromAccountBalance);
                        toAccountEntity.setBalance(newToAccountBalance);
                    } catch (Exception ex) {
                        logger.log(Level.SEVERE, "exception occurs while transfer operation fromId="+fromAccountId+" - toId="+toAccountId+" with amount="+amount);
                        fromAccountEntity.setBalance(fromAccountBalance);
                        toAccountEntity.setBalance(toAccountBalance);
                        throw ex;
                    }
                    logger.log(Level.INFO, "transfer completed successfully from id="+fromAccountId + " to id="+toAccountId + " amount" +amount);
                }
            } catch (ApplicationException.NoSufficientBalanceException ex) {
                logger.log(Level.SEVERE,"Account id="+fromAccountId+ " balance="+fromAccountEntity.getBalance() +" has not enough balance for amount="+amount,ex);
                throw ex;
            }
        }



    }

    private void validateAmount(double amount) {
        if(amount <= 0 ) {
            throw new ApplicationException.InvalidAmountForTransfer(amount);
        }
    }
}
