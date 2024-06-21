package com.example.thymeleaf_Demo.controller;

import com.example.thymeleaf_Demo.model.Student;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class StudentController {
    @Value("${countries}") // to inject from properties file
    private List<String> countries;

    @Value("${languages}")
    private List<String> languages;

    @Value("${systems}")
    private List<String> systems;

    @GetMapping("/showStudentForm")
    public String showForm(Model model) {
        // create a new student obj
        Student theStudent = new Student();
        model.addAttribute("student", theStudent);
        // add list of countries  to model
        model.addAttribute("countries", countries);
        model.addAttribute("languages", languages);
        model.addAttribute("systems", systems);
        return "student-form";
    }

    @PostMapping("processStudentForm")
    public String processForm(@ModelAttribute("student") Student theStudent) {
        System.out.println(theStudent.getFirstName() + " " + theStudent.getLastName() + theStudent.getCountry()+ theStudent.getSystem());
        return "student-confirmation";
    }
}