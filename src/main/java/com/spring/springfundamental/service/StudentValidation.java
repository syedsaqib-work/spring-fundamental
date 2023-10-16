package com.spring.springfundamental.service;

import com.spring.springfundamental.dao.PersonDao;
import com.spring.springfundamental.dao.StudentDao;
import com.spring.springfundamental.entity.Person;
import com.spring.springfundamental.entity.Student;
import com.spring.springfundamental.exception.InvalidParamException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;

@Service
public class StudentValidation {
    @Autowired
    StudentDao studentDao;

    public boolean validateStudent(Student student) {
        if (student.getMarks() < 35) {
            return false;
        } else {
            return true;
        }
    }

    public void handleError(Student student) {
        student.setStatus("FAILED");
        studentDao.saveStudent(student);
        System.out.println("Saved to DB");
        throw new InvalidParamException("Marks are below 35");

    }
}
