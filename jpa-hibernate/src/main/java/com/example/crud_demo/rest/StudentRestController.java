package com.example.crud_demo.rest;

import com.example.crud_demo.rest.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentRestController {
    ArrayList<Student> students;

    @PostConstruct
    public void loadData() {
        students = new ArrayList<>();
        students.add(new Student("ravi", 19212));
        students.add(new Student("ram", 19213));
        students.add(new Student("rai", 19214));
    }

    @GetMapping("/student/{studentId}")
    public Student getStudents(@PathVariable int studentId) {
        if (studentId >= students.size() || studentId < 0) {
            throw new StudentNotFoundException("student not found - " + studentId);
        }
        return students.get(studentId);// studentId is index
    }

//    @ExceptionHandler
//    public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException exc) {
//        StudentErrorResponse error = new StudentErrorResponse();
//        error.setStatus(HttpStatus.NOT_FOUND.value());
//        error.setMessage(exc.getMessage());
//        error.setTimestamp(System.currentTimeMillis());
//        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
//    }
//
//    @ExceptionHandler
//    public ResponseEntity<StudentErrorResponse> handleException(Exception exc) {
//        StudentErrorResponse error = new StudentErrorResponse();
//        error.setStatus(HttpStatus.BAD_REQUEST.value());
//        error.setMessage(exc.getMessage());
//        error.setTimestamp(System.currentTimeMillis());
//        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
//    }
}
