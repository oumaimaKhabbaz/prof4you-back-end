package com.prof4you.app.services.impl;

import com.prof4you.app.entities.Account;
import com.prof4you.app.entities.Prof;
import com.prof4you.app.entities.Role;
import com.prof4you.app.entities.Subject;
import com.prof4you.app.repositories.ProfRepository;
import com.prof4you.app.repositories.RoleRepository;
import com.prof4you.app.services.api.ProfService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ProfServiceImpl implements ProfService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProfServiceImpl.class);

    private ProfRepository profRepository;
    private RoleRepository roleRepository;

    @Autowired
    ProfServiceImpl(final ProfRepository profRepository, final  RoleRepository roleRepository){
        this.profRepository = profRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public Prof create(Prof prof) {
        Account account = prof.getAccount();
        Collection<Role> roles = account.getRoles();
        roles.add(roleRepository.findByRoleName("PROF"));
        account.setRoles(roles);
        return profRepository.save(prof);
    }

    @Override
    public Optional<Prof> findProfById(Long id) {
        return profRepository.findById(id);
    }

    @Override
    public Prof update(Prof prof, Long id) {
        Optional<Prof> optionalProfToUpdate = this.profRepository.findById(id);
        if(optionalProfToUpdate.isPresent()){
            Prof profToupdate =  optionalProfToUpdate.get();
            profToupdate.setAccount(prof.getAccount());
            profToupdate.setHourlyPrice(prof.getHourlyPrice());
            profToupdate.setId(id);
            profToupdate.setLessonType(prof.getLessonType());
            profToupdate.setLevels(prof.getLevels());
            profToupdate.setProfResume(prof.getProfResume());
            profToupdate.setReviews(prof.getReviews());
            profToupdate.setSubjects(profToupdate.getSubjects());
            profToupdate.setSubscription(prof.getSubscription());
            profToupdate.setTeachMethod(prof.getTeachMethod());
            return this.profRepository.save(profToupdate);
        }
        return prof;
    }

    @Override
    public Boolean delete(Long id) {
        this.profRepository.deleteById(id);
        return null;
    }

    @Override
    public List<Prof> findProfBySubject(String subject) {
        Set<Subject> subjects = new HashSet<>();
        if (subject.equals("all")){
            return profRepository.findDistinctBySubjectsIn(new HashSet<Subject>(Arrays.asList(Subject.values())));
        }else {
            subjects.add(Subject.valueOf(subject));
        }
        return profRepository.findDistinctBySubjectsIn(subjects);
    }
}
