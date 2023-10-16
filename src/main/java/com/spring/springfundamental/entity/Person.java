package com.spring.springfundamental.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="PERSON")
public class Person {
    @Id
    @Column(name = "ID")
    Integer id;

    @Column(name = "PNAME")
    String pName;

    @Column(name = "AGE")
    Integer age;

    @Column(name = "STATUS")
    String status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", pName='" + pName + '\'' +
                ", age=" + age +
                ", status='" + status + '\'' +
                '}';
    }
}
