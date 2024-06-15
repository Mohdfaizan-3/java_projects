package com.example.demo;

import org.springframework.stereotype.Component;

@Component // makes class as spring bean
public class CricketCoach implements Coach{
    @Override
    public String getDailyWorkOut() {
        return "practice bowl for 5 min";
    }
}
