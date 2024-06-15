package com.example.demo.common;

import org.springframework.stereotype.Component;

@Component
public class BaseBallCoach implements Coach{
    public String getDailyWorkOut() {
        return "30 min in baseball";
    }
}
