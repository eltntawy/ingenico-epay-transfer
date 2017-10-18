package com.ingenico.pay.test;

import com.ingenico.pay.dto.AccountDto;
import com.ingenico.pay.service.AccountService;
import com.ingenico.pay.service.TransferService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.logging.Logger;

/**
 * Created by mohamedtantawy on 10/18/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ConcurrentTransferRestServiceTest {


    private static final Logger logger = Logger.getLogger(ConcurrentTransferRestServiceTest.class.getSimpleName());

    @Autowired
    WebApplicationContext webApplicationContext;

    @Autowired
    AccountService accountService;

    @Autowired
    TransferService transferService;

    MockMvc mockMvc;


    private static AccountDto accountDto1;
    private static AccountDto accountDto2;

    String accountName1 = "account 1";
    String accountName2 = "account 2";

    double balance = 1000d;
    double amount = 10d;

    /**
     * setup the env and create two vitrual accounts with initial balance @balance
     */
    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        accountDto1 = accountService.create(accountName1, balance);
        accountDto2 = accountService.create(accountName2, balance);
    }

    /*
     * trying to make multiple transfer from @accountDto1 to @accountDto2 by @amount
     */
    @Test
    public void makeTransfer() throws Exception {

        for (int i = 0; i < 100; i++) {
            new Thread("thread 1->2 [" + i+"]") {
                @Override
                public void run() {
                    transferService.validateAccountAndDoTransfer(accountDto1.getId(), accountDto2.getId(), amount);
                }
            }.start();
        }

        Thread.sleep(5000);

        AccountDto account2 = accountService.find(accountDto2.getId());

        Assert.assertEquals( accountDto1.getBalance()+accountDto2.getBalance(),account2.getBalance(),0.0);

    }
}
