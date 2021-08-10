package com.prof4you.app.services.api;

import com.prof4you.app.entities.Subject;

import java.util.Optional;

public interface SubjectService {
    
    public Subject create(Subject subject);

    public Optional<Subject> findUserById(Long id);

    public Subject update(Subject subject);

    public Boolean delete(Long id);
}
