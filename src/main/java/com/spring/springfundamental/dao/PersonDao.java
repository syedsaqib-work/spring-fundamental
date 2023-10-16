package com.spring.springfundamental.dao;

import com.spring.springfundamental.entity.Person;
import com.spring.springfundamental.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonDao {

    @Autowired
    PersonRepository personRepository;

    public void savePerson(Person person){
        System.out.println("Inside PersonDao - " + person.getId());
        Person save = personRepository.save(person);
        System.out.println("save => " + save.toString());
    }
}
