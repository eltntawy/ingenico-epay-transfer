package com.ingenico.pay.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by mohamedtantawy on 10/17/17.
 */
public class ApplicationException {

    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such Account")
    public static class AccountNotFoundException extends RuntimeException {
        public AccountNotFoundException(String id) {
            super("account id=" +id + " not found");
        }
    }


    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "No sufficient Balance")
    public static class NoSufficientBalanceException extends RuntimeException {

        public NoSufficientBalanceException(String id, double amount) {
            super("account id=" + id + " has not enough balance for amount=" + amount);
        }
    }


    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Invalid Amount For Transfer")
    public static class InvalidAmountForTransfer extends RuntimeException {
        public InvalidAmountForTransfer(double amount) {
            super("amount="+amount+" is not valid to proceed with transfer");
        }
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Illegal Initial Balance")
    public static class IllegalInitialBalance extends RuntimeException {
        public IllegalInitialBalance(double balance) {
            super("balance="+balance+" is not valid initial balance for account");
        }
    }
}
