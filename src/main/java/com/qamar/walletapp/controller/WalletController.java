package com.qamar.walletapp.controller;

import com.qamar.walletapp.entity.Wallet;
import com.qamar.walletapp.service.ValidationErrorService;
import com.qamar.walletapp.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/wallet")
@CrossOrigin
public class WalletController {
    @Autowired
    private WalletService walletService;

    @Autowired
    private ValidationErrorService validationService;

    @GetMapping
    public  ResponseEntity<?> getAll()
    {
        return  new ResponseEntity<>(walletService.getAll(),HttpStatus.OK);

    }

   @GetMapping("/{id}")
   public  ResponseEntity<?> getById(@PathVariable Long id)
   {
       return new ResponseEntity<>(walletService.getById(id),HttpStatus.OK);

   }
    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Wallet wallet, BindingResult result)
    {

        ResponseEntity error = validationService.Validate(result);
        if(error !=null ) return  error;

      Wallet walletsaved  =walletService.creat(wallet);

      return  new ResponseEntity<Wallet>(walletsaved,HttpStatus.CREATED);
      }



      @DeleteMapping("/{id}")
    public  ResponseEntity<?> delete(@PathVariable Long id)
      {
          walletService.delete(id);
          return  new ResponseEntity<>(HttpStatus.OK);
      }




    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,@Valid @RequestBody Wallet wallet, BindingResult result)
    {

        ResponseEntity error = validationService.Validate(result);
        if(error !=null ) return  error;

        wallet.setId(id);
        Wallet walletsaved  =walletService.udate(wallet);

        return  new ResponseEntity<Wallet>(walletsaved,HttpStatus.OK);
    }


}

