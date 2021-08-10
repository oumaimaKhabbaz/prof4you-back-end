package com.prof4you.app.services.impl;

import com.prof4you.app.entities.Student;
import com.prof4you.app.repositories.ReviewRepository;
import com.prof4you.app.repositories.StudentRepository;
import com.prof4you.app.services.api.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReviewServiceImpl.class);

    private StudentRepository studentRepository;

    @Autowired
    StudentServiceImpl (final StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }


    @Override
    public Student create(Student student) {
        return studentRepository.save(student) ;
    }

    @Override
    public Optional<Student> findStudentById(Long id) {
        return studentRepository.findById(id);
    }

    @Override
    public Student update(Student student, Long id) {
        Optional<Student> optionalStudentToUpdate = studentRepository.findById(id);
        if( optionalStudentToUpdate.isPresent()){
            Student studentToUpdate = optionalStudentToUpdate.get();
            student.setAccount(student.getAccount());
            student.setId(id);
            return studentRepository.save(studentToUpdate);
        }
        return student;
    }

    @Override
    public Boolean delete(Long id) {
        studentRepository.deleteById(id);
        return null;
    }
}
