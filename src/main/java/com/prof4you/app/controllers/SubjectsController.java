package com.prof4you.app.controllers;

import com.prof4you.app.entities.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("subjects")
@CrossOrigin("*")
public class SubjectsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(
            SubjectsController.class);


    @GetMapping
    public List<Subject> getAllSubjects(){
        return Arrays.asList(Subject.values());
    }
}
