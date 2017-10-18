package com.ingenico.pay.rest;

import com.ingenico.pay.dto.AccountDto;
import com.ingenico.pay.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import javax.xml.ws.Response;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by mohamedtantawy on 10/17/17.
 */
@RestController
@RequestMapping("/account")
public class AccountRest {

    private static final Logger LOGGER = Logger.getLogger(AccountRest.class.getSimpleName());

    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ResponseEntity<AccountDto> find(@PathVariable("id") String id) {

        AccountDto accountDto = accountService.find(id);

        if(accountDto != null) {
           ResponseEntity<AccountDto> response = new ResponseEntity<AccountDto>(accountDto, HttpStatus.FOUND);

           return response;
        }

        return null;
    }

    @RequestMapping(value = "/",method = RequestMethod.POST)
    @PostMapping
    public ResponseEntity<AccountDto> create (@RequestParam("accountName") String accountName,@RequestParam("balance") Double balance) {

        LOGGER.log(Level.INFO,"account creation request with: accountName="+accountName+ " - balance="+balance);

        AccountDto accountDto = accountService.create(accountName,balance);

        LOGGER.log(Level.INFO,"Account Created="+accountDto);

        ResponseEntity<AccountDto> response = new ResponseEntity<AccountDto>(accountDto, HttpStatus.OK);
        return response;
    }


    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    @PostMapping
    public ResponseEntity<AccountDto> delete (@PathVariable("id") String id) {

        LOGGER.log(Level.INFO,"request to delete account id="+id);

        AccountDto accountDto = accountService.delete(id);

        LOGGER.log(Level.INFO,"account accountDto="+accountDto);

        ResponseEntity<AccountDto> response = new ResponseEntity<AccountDto>(accountDto, HttpStatus.OK);
        return response;
    }


    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<AccountDto>> findAll() {

        List<AccountDto> accountDtos = accountService.findAll();

        if(accountDtos != null) {
            ResponseEntity<List<AccountDto>> response = new ResponseEntity<List<AccountDto>>(accountDtos, HttpStatus.FOUND);

            return response;
        }

        ResponseEntity response = new ResponseEntity<>("No Account Founded", HttpStatus.FOUND);

        return response;
    }

}
