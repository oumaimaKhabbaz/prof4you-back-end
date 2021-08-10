package com.prof4you.app.controllers;

import com.prof4you.app.entities.Prof;
import com.prof4you.app.services.api.ProfService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/prof")
@CrossOrigin("*")
public class ProfController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProfController.class);

    private ProfService profService;

    @Autowired
    ProfController( final  ProfService profService){
        this.profService = profService;
    }

    @PostMapping
    public ResponseEntity createProf(@RequestBody Prof prof){
        return new ResponseEntity(profService.create(prof), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getProf(@RequestParam("id") Long profId){
        final Optional<Prof> profById = profService.findProfById(profId);
        if (profById.isPresent()){
            return new ResponseEntity(profById.get(), HttpStatus.OK);
        }
        return  new ResponseEntity( "Prof with id= "+ profId + " not found!", HttpStatus.NOT_FOUND);
    }


    @CrossOrigin("*")
    @GetMapping("/search")
    public ResponseEntity getProf(@RequestParam String subject){
        final List<Prof> profsBySubject = profService.findProfBySubject(subject);
        return new ResponseEntity(profsBySubject, HttpStatus.OK);
    }



    @PutMapping
    public ResponseEntity updateProf(@RequestBody Prof prof,  @PathVariable Long id){
        return  new ResponseEntity(profService.update(prof, id), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity deleteProf(@PathVariable Long id){
        return  new ResponseEntity(profService.delete(id), HttpStatus.OK);
    }
}
