package com.prof4you.app.repositories;

import com.prof4you.app.entities.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {

    Account findByEmail(String email);
}
