package com.spring.springfundamental.service;

import com.spring.springfundamental.dao.PersonDao;
import com.spring.springfundamental.dao.StudentDao;
import com.spring.springfundamental.entity.Person;
import com.spring.springfundamental.entity.Student;
import com.spring.springfundamental.repository.PersonRepository;
import com.spring.springfundamental.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    PersonValidation personValidation;

    @Autowired
    PersonDao personDao;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    StudentValidation studentValidation;

    @Autowired
    StudentDao studentDao;

    @Override
    public List<Person> getAllPersons() {
        List<Person> allPersons = personRepository.findAll();
        return allPersons;
    }

    @Override
    public Person addPerson(Person person) {
        if(personValidation.validatePerson(person)){
            person.setStatus("SUCCESS");
            personDao.savePerson(person);
        }else{
            personValidation.handleError(person);
        }
        return person;
    }

    @Override
    public List<Student> getAllStudents() {
        List<Student> allStudents= studentRepository.findAll();
        return allStudents;
    }

    @Override
    public Student addStudent(Student student) {
        if(studentValidation.validateStudent(student)){
            student.setStatus("SUCCESS");
            studentDao.saveStudent(student);
        }else{
            studentValidation.handleError(student);
        }
        return student;
    }
}
