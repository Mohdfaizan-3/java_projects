package com.example.thymeleaf_Demo.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloWorldController {

    @GetMapping("/showForm")
    public String showForm() {
        return "helloworld-form";
    }

//    @GetMapping("/processForm")
//    public String processForm() {
//        return "helloWorld";
//    }

    @GetMapping("/processFormVersionTwo")
    //read form data
    public String readData(HttpServletRequest request, Model model) {
        // read the req param from html form
        String name = request.getParameter("studentName");
        // convert the data to caps
        name = name.toUpperCase();
        // create the message
        String result = "hi " + name;
        // add message to model
        model.addAttribute("message", result);
        return "helloworld";
    }


    // add data to model

}
