package com.ingenico.pay.rest;

import com.ingenico.pay.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by mohamedtantawy on 10/17/17.
 */
@RestController
@RequestMapping("transfer")
public class TransferRest {

    @Autowired
    TransferService transferService;



    @RequestMapping(value = "/{fromAccount}/{toAccount}", method = RequestMethod.POST)
    public ResponseEntity<String> transferRequest(@PathVariable("fromAccount") String fromAccountId,
                                          @PathVariable("toAccount") String toAccountId,
                                          @RequestParam("amount") double amount) {

        transferService.validateAccountAndDoTransfer(fromAccountId,toAccountId,amount);

        String message = "Transfer Completed successfully";
        ResponseEntity<String> response = new ResponseEntity<String>(message, HttpStatus.OK);

        return response;
    }

}
