package com.prof4you.app.services.api;

import com.prof4you.app.entities.Prof;

import java.util.List;
import java.util.Optional;

public interface ProfService {

    public Prof create(Prof Prof);

    public Optional<Prof> findProfById(Long id);

    public Prof update(Prof Prof, Long id);

    public Boolean delete(Long id);

    List<Prof> findProfBySubject(String subject);
}
