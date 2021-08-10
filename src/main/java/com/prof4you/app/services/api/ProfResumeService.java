package com.prof4you.app.services.api;

import com.prof4you.app.entities.ProfResume;

import java.util.Optional;

public interface ProfResumeService {

    public ProfResume create(ProfResume profResume);

    public Optional<ProfResume> findProfResumeById(Long id);

    public ProfResume update(ProfResume profResume, long id);

    public Boolean delete(Long id);
}
