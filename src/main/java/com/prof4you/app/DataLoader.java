package com.prof4you.app;

import com.prof4you.app.entities.Account;
import com.prof4you.app.entities.Role;
import com.prof4you.app.repositories.AccountRepository;
import com.prof4you.app.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataLoader implements ApplicationRunner {

    private RoleRepository roleRepository;
    private AccountRepository accountRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public DataLoader(RoleRepository roleRepository, AccountRepository accountRepository,
        BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.roleRepository = roleRepository;
        this.accountRepository =accountRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public void run(ApplicationArguments args) {
        /// create roles
        roleRepository.save(new Role("PROF"));
        roleRepository.save(new Role("ADMIN"));
        roleRepository.save(new Role("STUDENT"));

        /// create account
        Account account = new Account();
        account.setPassword(bCryptPasswordEncoder.encode("12345"));
        account.setEmail("admin@admin.com");
        List<Role> roles = new ArrayList<Role>();
        roles.add(roleRepository.findByRoleName("ADMIN"));
        account.setRoles(roles);
        account.setFirstname("admin");
        account.setLastname("admin");
        accountRepository.save(account);
    }
}