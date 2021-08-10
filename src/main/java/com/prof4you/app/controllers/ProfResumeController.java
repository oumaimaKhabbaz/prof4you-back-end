package com.prof4you.app.controllers;

import com.prof4you.app.entities.Prof;
import com.prof4you.app.entities.ProfResume;
import com.prof4you.app.services.api.ProfResumeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/profResume")
@CrossOrigin("*")
public class ProfResumeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountController.class);

    private ProfResumeService profResumeService;

    @Autowired
    ProfResumeController(final ProfResumeService profResumeService) {
        this.profResumeService = profResumeService;
    }

    @PostMapping
    public ResponseEntity createResume(@RequestBody ProfResume profResume){
        return  new ResponseEntity(profResumeService.create(profResume), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getResume(@PathVariable Long id){
        Optional<ProfResume> profResume = profResumeService.findProfResumeById(id);
        if(profResume.isPresent()){
            return new ResponseEntity(profResume.get(), HttpStatus.OK);
        }
        return  new ResponseEntity("Resume with id= "+ id + " not found!", HttpStatus.NOT_FOUND);
    }


    @PutMapping
    public ResponseEntity updateResume(@RequestBody ProfResume profResume, @PathVariable Long id){
        return  new ResponseEntity( profResumeService.update(profResume, id), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity deleteResume(@PathVariable Long id){
        return  new ResponseEntity(profResumeService.delete(id), HttpStatus.OK);
    }

}
