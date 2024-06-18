package com.example.crud_demo.rest;

import com.example.crud_demo.rest.entity.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentRestController {

    @GetMapping("/student")
    public List<Student> getStudents() {
        List<Student> students = new ArrayList<>();
        students.add(new Student("ravi", 19212));
        students.add(new Student("ram", 19213));
        students.add(new Student("rai", 19214));
        return students;
    }
}
