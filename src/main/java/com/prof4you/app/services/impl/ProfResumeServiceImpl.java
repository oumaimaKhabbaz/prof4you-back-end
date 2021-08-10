package com.prof4you.app.services.impl;

import com.prof4you.app.entities.ProfResume;
import com.prof4you.app.repositories.ProResumeRepository;
import com.prof4you.app.services.api.ProfResumeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfResumeServiceImpl implements ProfResumeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProfResumeServiceImpl.class);

    private ProResumeRepository proResumeRepository;

    @Autowired
    private ProfResumeServiceImpl(final ProResumeRepository proResumeRepository){
        this.proResumeRepository = proResumeRepository;
    }


    @Override
    public ProfResume create(ProfResume profResume) {
        return this.proResumeRepository.save(profResume);
    }

    @Override
    public Optional<ProfResume> findProfResumeById(Long id) {
        return this.proResumeRepository.findById(id);
    }

    @Override
    public ProfResume update(ProfResume profResume, long id) {
        Optional<ProfResume> optionalProfResumeToUpdate = proResumeRepository.findById(id);
        if(optionalProfResumeToUpdate.isPresent()){
            ProfResume profResumeToUpdate = optionalProfResumeToUpdate.get();
            profResumeToUpdate.setDescription(profResume.getDescription());
            profResumeToUpdate.setId(id);
            profResumeToUpdate.setSubTitle(profResume.getSubTitle());
            profResumeToUpdate.setDescription(profResume.getDescription());
            return  proResumeRepository.save(profResumeToUpdate);
        }
        return profResume;
    }

    @Override
    public Boolean delete(Long id) {
        proResumeRepository.deleteById(id);
        return true;
    }
}
