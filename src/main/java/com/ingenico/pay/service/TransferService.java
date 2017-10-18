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

    public void validateAccountAndDoTransfer(final String fromAccountId,final String toAccountId, final Double amount) {

        accountService.validateAccount(fromAccountId);
        accountService.validateAccount(toAccountId);
        validateAmount(amount);

        AccountEntity fromAccountEntity = accountService.findEntity(fromAccountId);

        synchronized (fromAccountEntity) {
            try {
                accountService.checkAccountHasSufficientBalance(fromAccountId, amount);
                AccountEntity toAccountEntity = accountService.findEntity(fromAccountId);
                synchronized (toAccountEntity) {

                    Double newFromAccountBalance = fromAccountEntity.getBalance() - amount;
                    fromAccountEntity.setBalance(newFromAccountBalance);

                    Double newToAccountBalance = toAccountEntity.getBalance() +amount;
                    toAccountEntity.setBalance(newToAccountBalance);
                    accountService.save(toAccountEntity);
                    logger.log(Level.INFO, "transfer completed successfully from id="+fromAccountId + " to id="+toAccountId + " amount" +amount);
                }
                accountService.save(fromAccountEntity);
            } catch (ApplicationException.NoSufficientBalanceException ex) {
                logger.log(Level.SEVERE,"Account id="+fromAccountId+ " balance="+fromAccountEntity.getBalance() +" has not enough balance for amount="+amount,ex);
                throw ex;
            }
        }



    }

    private void validateAmount(Double amount) {
        if(amount <= 0 ) {
            throw new ApplicationException.InvalidAmountForTransfer(amount);
        }
    }
}
