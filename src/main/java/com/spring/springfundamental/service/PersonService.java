package com.spring.springfundamental.service;

import com.spring.springfundamental.entity.Person;
import com.spring.springfundamental.entity.Student;
import com.spring.springfundamental.exception.InvalidParamForPersonException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//@Transactional
@Transactional(rollbackFor = Throwable.class, noRollbackFor = InvalidParamForPersonException.class)
public interface PersonService{

     List<Person> getAllPersons();

     Person addPerson(Person person);

     List<Student> getAllStudents();

     Student addStudent(Student student);
}
