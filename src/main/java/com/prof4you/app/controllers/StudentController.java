package com.prof4you.app.controllers;

import com.prof4you.app.dto.StudentDto;
import com.prof4you.app.entities.Account;
import com.prof4you.app.entities.Prof;
import com.prof4you.app.entities.ProfResume;
import com.prof4you.app.entities.Student;
import com.prof4you.app.services.api.AccountService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/student")
public class StudentController {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentController.class);

    private StudentService studentService;
    private AccountService accountService;

    @Autowired
    StudentController( final StudentService studentService, final  AccountService accountService){
        this.studentService = studentService;
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity createStudent(@RequestParam("email") String email, @RequestBody StudentDto student){
        Account account = accountService.findAccountByEmail(email);
        Student student1 = new Student();
        account.setAddress(student.getAddress());
        account.setBirthDay(student.getBirthDay());
        account.setFirstname(student.getFirstname());
        account.setLastname(student.getLastname());
        account.setPhoneNumber(student.getPhoneNumber());
        account.setPhoto(student.getPhoto());
        student1.setAccount(account);
        return  new ResponseEntity( studentService.create(student1), HttpStatus.OK);
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
