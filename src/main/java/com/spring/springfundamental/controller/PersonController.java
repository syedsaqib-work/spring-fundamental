package com.spring.springfundamental.controller;

import com.spring.springfundamental.entity.Person;
import com.spring.springfundamental.entity.Response;
import com.spring.springfundamental.entity.Student;
import com.spring.springfundamental.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class PersonController {

    @Autowired
    PersonService personService;


    @GetMapping("/getAllPersons")
    public Response GetPersons(){
        Response response = new Response();
        response.setObject(personService.getAllPersons());
        return response;
    }

    @PostMapping("/addPerson")
    public Response addPerson(@RequestBody Person person){
        Response response = new Response();
        response.setObject(personService.addPerson(person));

        return response;
    }

    @GetMapping("/getAllStudents")
    public Response GetAlLStudents(){
        Response response = new Response();
        response.setObject(personService.getAllStudents());
        return response;
    }

    @PostMapping("/addStudent")
    public Response addPerson(@RequestBody Student student){
        Response response = new Response();
        response.setObject(personService.addStudent(student));

        return response;
    }
}
