package com.prof4you.app.repositories;

import com.prof4you.app.entities.Prof;
import com.prof4you.app.entities.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface ProfRepository extends JpaRepository<Prof, Long> {

    List<Prof> findDistinctBySubjectsIn(Set<Subject> subject);
}
