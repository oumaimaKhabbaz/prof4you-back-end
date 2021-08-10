package com.prof4you.app.services.api;

import com.prof4you.app.entities.Account;

import java.util.Optional;

public interface AccountService {

    public Account create(Account account);

    public Optional<Account> findAccountById(Long id);

    public Account update(Account account, Long id);

    public Boolean delete(Long id);


    Account findAccountByEmail(String email);
}
