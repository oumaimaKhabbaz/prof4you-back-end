package com.prof4you.app.services.impl;

import com.prof4you.app.entities.Account;
import com.prof4you.app.repositories.AccountRepository;
import com.prof4you.app.services.api.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountServiceImpl.class);

    private AccountRepository accountRepository;

    @Autowired
    private AccountServiceImpl(final AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    @Override
    public Account create(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public Optional<Account> findAccountById(Long id) {
        return accountRepository.findById(id);
    }

    @Override
    public Account update(Account account, Long id) {
        Optional<Account> optionalAccount =  accountRepository.findById(id);
        if(optionalAccount.isPresent()){
            Account accountToUpdate =  optionalAccount.get();
            accountToUpdate.setEmail(account.getEmail());
            accountToUpdate.setFirstname(account.getFirstname());
            accountToUpdate.setLastname(account.getLastname());
            accountToUpdate.setPassword(account.getPassword());
            accountToUpdate.setId(id);
            return accountRepository.save(accountToUpdate);
        }
        return accountRepository.save(account);
    }

    @Override
    public Boolean delete(Long id) {
        accountRepository.deleteById(id);
        return true;
    }

    @Override
    public Account findAccountByEmail(String email) {
        return accountRepository.findByEmail(email);
    }


}
