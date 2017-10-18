package com.ingenico.pay.test;

import com.ingenico.pay.dto.AccountDto;
import com.ingenico.pay.rest.TransferRest;
import com.ingenico.pay.service.AccountService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.logging.Logger;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by mohamedtantawy on 10/18/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TransferOverdrawnTest {
    private static final Logger logger = Logger.getLogger(TransferOverdrawnTest.class.getSimpleName());

    @Autowired
    WebApplicationContext webApplicationContext;

    @Autowired
    AccountService accountService;


    MockMvc mockMvc;


    private static AccountDto accountDto1;
    private static AccountDto accountDto2;

    String accountName1 = "account 1";
    String accountName2 = "account 2";

    double balance = 10d;


    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        accountDto1 = accountService.create(accountName1, balance);
        accountDto2 = accountService.create(accountName2, balance);
    }

    @Test
    public void checkOverdrawn() throws Exception {

        mockMvc.perform(post("/transfer/" + accountDto1.getId() + "/" + accountDto2.getId())
                .param("amount", Double.toString(balance)))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/plain;charset=UTF-8"))
                .andExpect(content().string(TransferRest.TRANSFER_SUCCESS_MESSAGE));


        mockMvc.perform(post("/transfer/" + accountDto1.getId() + "/" + accountDto2.getId())
                .param("amount", Double.toString(balance)))
                .andExpect(status().isBadRequest());

    }


    @After
    public void destroyAccountData() throws Exception {
        accountService.delete(accountDto1.getId());
        accountService.delete(accountDto2.getId());
    }
}
