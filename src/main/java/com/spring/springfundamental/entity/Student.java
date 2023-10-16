package com.spring.springfundamental.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="STUDENT")
public class Student {
    @Id
    @Column(name = "ID")
    Integer id;

    @Column(name = "STUDENT_NAME")
    String studentName;

    @Column(name = "MARKS")
    Integer marks;

    @Column(name = "STATUS")
    String status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Integer getMarks() {
        return marks;
    }

    public void setMarks(Integer marks) {
        this.marks = marks;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", studentName='" + studentName + '\'' +
                ", marks=" + marks +
                ", status='" + status + '\'' +
                '}';
    }
}
