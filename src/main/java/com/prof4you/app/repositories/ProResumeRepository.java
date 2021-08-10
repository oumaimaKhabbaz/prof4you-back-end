package com.prof4you.app.repositories;

import com.prof4you.app.entities.ProfResume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProResumeRepository extends JpaRepository<ProfResume, Long> {
}
