package com.ingenico.pay.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by mohamedtantawy on 10/17/17.
 */
public class ApplicationException {

    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such Account")
    class AccountNotFoundException extends RuntimeException {

    }


    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "No sufficient Balance")
    class NoSufficientBalanceException extends RuntimeException {

    }


}
