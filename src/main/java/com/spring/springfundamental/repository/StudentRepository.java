package com.spring.springfundamental.repository;

import com.spring.springfundamental.entity.Person;
import com.spring.springfundamental.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
}
