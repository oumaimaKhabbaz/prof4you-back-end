package com.prof4you.app.controllers;

import com.prof4you.app.entities.Student;
import com.prof4you.app.services.api.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/student")
public class StudentController {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentController.class);

    private StudentService studentService;

    @Autowired
    StudentController( final StudentService studentService){
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity createStudent(@RequestBody Student student){
        return  new ResponseEntity( studentService.create(student), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getStudent(@PathVariable Long id) {
        final Optional<Student> userById = studentService.findStudentById(id);
        if( userById.isPresent()){
            new ResponseEntity(userById.get(), HttpStatus.OK);
        }
        return  new ResponseEntity("Student with id= "+ id + " not found!", HttpStatus.OK);
    }


    @PutMapping
    public ResponseEntity updateStudent(@RequestBody Student student, @PathVariable Long id){
        return  new ResponseEntity(studentService.update(student, id) , HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity deleteStudent(@PathVariable Long id){
        return  new ResponseEntity(studentService.delete(id), HttpStatus.OK);
    }
}
