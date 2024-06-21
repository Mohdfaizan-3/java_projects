package com.example.thymeleaf_Demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Demo_Controller {
    @GetMapping("/demo")
    public String sayHello(Model model) {
        model.addAttribute("theDate", java.time.LocalDateTime.now());
        return "demo";
    }
}
