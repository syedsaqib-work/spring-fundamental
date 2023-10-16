package com.spring.springfundamental.service;

import com.spring.springfundamental.dao.PersonDao;
import com.spring.springfundamental.entity.Person;
import com.spring.springfundamental.exception.InvalidParamException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;

@Service
public class PersonValidation {
    @Autowired
    PersonDao personDao;

    public boolean validatePerson(Person person) {
        if (person.getAge() > 100) {
            return false;
        } else {
            return true;
        }
    }

    public void handleError(Person person) {
        person.setStatus("FAILED");
        personDao.savePerson(person);
        System.out.println("Saved to DB");
        throw new InvalidParamException("Age is above 100");

    }
}
