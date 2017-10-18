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

    /**
     * @param fromAccountId account will deduct amount
     * @param toAccountId   account will transfer amount
     * @param amount        amount will transfer
     * @throws ApplicationException.NoSufficientBalanceException
     */
    public void validateAccountAndDoTransfer(final String fromAccountId, final String toAccountId, final double amount) {

        accountService.validateAccount(fromAccountId);
        accountService.validateAccount(toAccountId);
        validateAmount(amount);

        AccountEntity fromAccountEntity = accountService.findEntity(fromAccountId);

        // make lock on fromAccount
        synchronized (fromAccountEntity) {
            try {
                accountService.checkAccountHasSufficientBalance(fromAccountId, amount);
                AccountEntity toAccountEntity = accountService.findEntity(toAccountId);

                // make lock on toAccount
                synchronized (toAccountEntity) {

                    // take backup for old balance
                    double fromAccountBalance = fromAccountEntity.getBalance();
                    double toAccountBalance = toAccountEntity.getBalance();

                    // deduct amount
                    double newFromAccountBalance = fromAccountEntity.getBalance() - amount;
                    double newToAccountBalance = toAccountEntity.getBalance() + amount;

                    try {
                        // set new balance
                        fromAccountEntity.setBalance(newFromAccountBalance);
                        toAccountEntity.setBalance(newToAccountBalance);
                    } catch (Exception ex) {
                        // rollback of any exception occurred
                        logger.log(Level.SEVERE, "exception occurs while transfer operation fromId=" + fromAccountId + " - toId=" + toAccountId + " with amount=" + amount);
                        fromAccountEntity.setBalance(fromAccountBalance);
                        toAccountEntity.setBalance(toAccountBalance);
                        throw ex;
                    }
                    logger.log(Level.INFO, "transfer completed from id=" + fromAccountId + " to id=" + toAccountId + " amount=" + amount + " fromAccount balance=" + fromAccountEntity.getBalance() + "toAccount balance=" + toAccountEntity.getBalance());

                }
            } catch (ApplicationException.NoSufficientBalanceException ex) {
                logger.log(Level.SEVERE, "Account id=" + fromAccountId + " balance=" + fromAccountEntity.getBalance() + " has not enough balance for amount=" + amount, ex);
                throw ex;
            }
        }


    }

    /**
     * @param amount amount to validate it is greater than 0
     * @throws ApplicationException.InvalidAmountForTransfer
     */

    private void validateAmount(double amount) {
        if (amount <= 0) {
            throw new ApplicationException.InvalidAmountForTransfer(amount);
        }
    }
}
