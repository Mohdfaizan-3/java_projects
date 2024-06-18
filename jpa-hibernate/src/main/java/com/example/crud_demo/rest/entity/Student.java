package com.example.crud_demo.rest.entity;
//Jackson, which is used by Spring Boot for JSON serialization,
// requires a no-argument constructor and getter methods to serialize an object
public class Student {
    private String name;
    private int rollNo;

    public Student() {
    }

    public Student(String name, int rollNo) {
        this.name = name;
        this.rollNo = rollNo;
    }

    // Getter methods
    public String getName() {
        return name;
    }

    public int getRollNo() {
        return rollNo;
    }
}
