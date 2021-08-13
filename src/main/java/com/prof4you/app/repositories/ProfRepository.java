package com.prof4you.app.repositories;

import com.prof4you.app.entities.Account;
import com.prof4you.app.entities.Prof;
import com.prof4you.app.entities.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface ProfRepository extends JpaRepository<Prof, Long> {

    List<Prof> findDistinctBySubjectsIn(Set<Subject> subject);

    List<Prof> findByAccount(Account account);

    @Query("SELECT p FROM Prof p WHERE p.account.email= 'admin@admin.com'")
    List<Prof> findProfByEmail();
}
