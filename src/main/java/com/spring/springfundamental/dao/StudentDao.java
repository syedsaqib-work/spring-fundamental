package com.spring.springfundamental.dao;

import com.spring.springfundamental.entity.Person;
import com.spring.springfundamental.entity.Student;
import com.spring.springfundamental.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentDao {
    @Autowired
    StudentRepository studentRepository;

    public void saveStudent(Student student){
        System.out.println("Inside StudentDao - " + student.getId());
        Student save = studentRepository.save(student);
        System.out.println("save => " + save.toString());
    }
}
