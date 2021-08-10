package com.prof4you.app.controllers;

import com.prof4you.app.entities.Account;
import com.prof4you.app.services.api.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("account")
@CrossOrigin("*")
public class AccountController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountController.class);

    private AccountService accountService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    AccountController(final  AccountService accountService, final BCryptPasswordEncoder bCryptPasswordEncoder){
        this.accountService = accountService;
        this.bCryptPasswordEncoder =  bCryptPasswordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity createUser(@RequestBody Account account){
        account.setPassword(bCryptPasswordEncoder.encode(account.getPassword()));
        final Account createdAccount = accountService.create(account);
        return new ResponseEntity(createdAccount, HttpStatus.CREATED); // 201
    }

    @GetMapping
    public ResponseEntity findUserById(@RequestParam("id") Long accountId){
        Optional<Account>  u  =  accountService.findAccountById(accountId);
        if (u.isPresent()){
            return  new ResponseEntity(u.get(), HttpStatus.OK); // http code 200
        }
        return  new ResponseEntity("Account with id= "+ accountId + " not found!",HttpStatus.NOT_FOUND); // http code 404
    }

    @DeleteMapping
    public ResponseEntity deleteUser(@RequestParam("id") Long accountId){
        final Boolean deleted = accountService.delete(accountId);
        if(deleted){
            return new ResponseEntity("Account is deleted!", HttpStatus.OK); // 200
        }
        return  new ResponseEntity("account with id= "+ accountId + " not found!", HttpStatus.NOT_FOUND); // 200
    }

    @PutMapping
    public ResponseEntity updateUser(@RequestBody Account account, @RequestParam Long id){
    final Account updatedAccount = accountService.update(account, id);
    return new ResponseEntity(updatedAccount, HttpStatus.OK); // 201
    }


}