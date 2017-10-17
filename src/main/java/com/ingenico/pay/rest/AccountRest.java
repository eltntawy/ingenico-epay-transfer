package com.ingenico.pay.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by mohamedtantawy on 10/17/17.
 */
@RestController
@RequestMapping("/account")
public class AccountRest {

    private static final Logger LOGGER = Logger.getLogger(AccountRest.class.getSimpleName());


    @RequestMapping("create")
    public String createAccount (@RequestParam("accountName") String accountName,@RequestParam("balance") Double balance) {

        LOGGER.log(Level.INFO,"accout creation reaquest with: accountName="+accountName+ " - balance="+balance);
        return "Account Created";
    }
}
