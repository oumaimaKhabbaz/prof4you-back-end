package com.prof4you.app;

import com.prof4you.app.entities.Account;
import com.prof4you.app.entities.Prof;
import com.prof4you.app.entities.Role;
import com.prof4you.app.repositories.AccountRepository;
import com.prof4you.app.repositories.ProResumeRepository;
import com.prof4you.app.repositories.ProfRepository;
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
    private ProfRepository profRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public DataLoader(RoleRepository roleRepository, AccountRepository accountRepository,
                      BCryptPasswordEncoder bCryptPasswordEncoder, ProfRepository profRepository) {
        this.roleRepository = roleRepository;
        this.accountRepository =accountRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.profRepository = profRepository;
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


        //
        Account account1 = new Account();
        account1.setPassword(bCryptPasswordEncoder.encode("12345"));
        account1.setEmail("prof@prof.com");
        List<Role> roles1 = new ArrayList<Role>();
        roles.add(roleRepository.findByRoleName("PROF"));
        account1.setRoles(roles1);
        account1.setFirstname("prof");
        account1.setLastname("prof");

        Prof prof =  new Prof();
        prof.setHourlyPrice(15);
        prof.setAccount(account1);
        profRepository.save(prof);

    }
}