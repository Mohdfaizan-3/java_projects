package com.example.thymeleaf_Demo.controller;

import com.example.thymeleaf_Demo.model.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class StudentController {
    @GetMapping("/showStudentForm")
    public String showForm(Model model) {
        // create a new student obj
        Student theStudent = new Student();
        model.addAttribute("student", theStudent);
        return "student-form";
    }

    @PostMapping("processStudentForm")
    public String processForm(@ModelAttribute("student") Student theStudent) {
        System.out.println(theStudent.getFirstName() + " " + theStudent.getLastName());
        return "student-confirmation";
    }
}
