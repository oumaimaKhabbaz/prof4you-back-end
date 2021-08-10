package com.prof4you.app.services.api;

import com.prof4you.app.entities.Student;

import java.util.Optional;

public interface StudentService {
    
    public Student create(Student student);

    public Optional<Student> findStudentById(Long id);

    public Student update(Student student, Long id);

    public Boolean delete(Long id);
    
}
