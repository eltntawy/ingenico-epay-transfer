package com.ingenico.pay.test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ingenico.pay.dto.AccountDto;
import com.ingenico.pay.service.AccountService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.logging.Logger;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by mohamedtantawy on 10/18/17.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountRestServiceTest {


    private static final Logger logger = Logger.getLogger(AccountRestServiceTest.class.getSimpleName());

    @Autowired
    WebApplicationContext webApplicationContext;

    MockMvc mockMvc;


    @Autowired
    AccountService accountService;

    private static AccountDto accountDto1;
    private static AccountDto accountDto2;

    String accountName1 = "account 1";
    String accountName2 = "account 2";

    double balance = 10d;


    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    /**
     *
     * trying to test create two account and assert the result
     */
    @Test
    public void accountCreation() throws Exception {

        String account1JsonStr = mockMvc.perform(post("/account/")
                .param("accountName", accountName1)
                .param("balance", Double.toString(balance))).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.name").value(accountName1))
                .andExpect(jsonPath("$.balance").value(balance)).andReturn().getResponse().getContentAsString();


        String account2JsonStr = mockMvc.perform(post("/account/")
                .param("accountName", accountName2)
                .param("balance", Double.toString(balance)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.name").value(accountName2))
                .andExpect(jsonPath("$.balance").value(balance)).andReturn().getResponse().getContentAsString();

        Gson gson = new GsonBuilder().create();
        accountDto1 = gson.fromJson(account1JsonStr, AccountDto.class);
        accountDto2 = gson.fromJson(account2JsonStr, AccountDto.class);
    }

    @After
    public void destroyAccountData() throws Exception {

        mockMvc.perform(delete("/account/" + accountDto1.getId())
                .param("accountName", accountName1)
                .param("balance", Double.toString(balance)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.name").value(accountName1));

        mockMvc.perform(delete("/account/" + accountDto2.getId())
                .param("accountName", accountName2)
                .param("balance", Double.toString(balance)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.name").value(accountName2));
    }

}
