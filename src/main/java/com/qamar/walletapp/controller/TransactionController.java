package com.qamar.walletapp.controller;


import com.qamar.walletapp.entity.Transaction;
import com.qamar.walletapp.service.TransactionService;
import com.qamar.walletapp.service.ValidationErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/transaction")
@CrossOrigin
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @Autowired
    private ValidationErrorService validationService;

    @GetMapping("/{wallet_id}")
    public ResponseEntity<?> getAll(@PathVariable long wallet_id)
    {
        return  new ResponseEntity<>(transactionService.getAll(wallet_id), HttpStatus.OK);

    }

    @GetMapping("/{wallet_id}/{id}")
    public ResponseEntity<?> getById(@PathVariable long wallet_id,@PathVariable long id)
    {
        return  new ResponseEntity<>(transactionService.getById(wallet_id,id), HttpStatus.OK);

    }
    @PostMapping("/{wallet_id}")
    public ResponseEntity<?> create(@PathVariable Long wallet_id, @Valid @RequestBody Transaction transaction, BindingResult result)
    {

        ResponseEntity error = validationService.Validate(result);
        if(error !=null ) return  error;

        Transaction transactionsaved  =transactionService.create(wallet_id,transaction);

        return  new ResponseEntity<Transaction>(transactionsaved,HttpStatus.CREATED);
    }



    @DeleteMapping("/{wallet_id}/{id}")
    public  ResponseEntity<?> delete(@PathVariable Long wallet_id,@PathVariable Long id)
    {
        transactionService.delete(wallet_id,id);
        return  new ResponseEntity<>(HttpStatus.OK);
    }




    @PutMapping("/{wallet_id}/{id}")
    public ResponseEntity<?> update(@PathVariable Long wallet_id,@PathVariable Long id, @Valid @RequestBody Transaction transaction, BindingResult result)
    {

        ResponseEntity error = validationService.Validate(result);
        if(error !=null ) return  error;

        transaction.setId(id);
        Transaction transactionsaved  =transactionService.update(wallet_id,transaction);

        return  new ResponseEntity<Transaction>(transactionsaved,HttpStatus.OK);
    }



}
