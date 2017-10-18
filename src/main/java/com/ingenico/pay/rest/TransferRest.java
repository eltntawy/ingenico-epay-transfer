package com.ingenico.pay.rest;

import com.ingenico.pay.exception.ApplicationException;
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

    public  static final String TRANSFER_SUCCESS_MESSAGE = "Transfer Completed successfully" ;
    @Autowired
    TransferService transferService;


    /**
     *
     * @param fromAccountId account will deduct amount
     * @param toAccountId account will transfer amount
     * @param amount amount will transfer
     * @return successful message
     * @throws ApplicationException.NoSufficientBalanceException
     */
    @RequestMapping(value = "/{fromAccount}/{toAccount}", method = RequestMethod.POST)
    public ResponseEntity<String> transferRequest(@PathVariable("fromAccount") String fromAccountId,
                                          @PathVariable("toAccount") String toAccountId,
                                          @RequestParam("amount") double amount)  throws ApplicationException.NoSufficientBalanceException{

        transferService.validateAccountAndDoTransfer(fromAccountId,toAccountId,amount);

        String message = TRANSFER_SUCCESS_MESSAGE;
        ResponseEntity<String> response = new ResponseEntity<String>(message, HttpStatus.OK);

        return response;
    }

}
