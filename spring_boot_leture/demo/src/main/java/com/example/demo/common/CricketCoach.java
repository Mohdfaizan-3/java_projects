package com.example.demo.common;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component // makes class as spring bean
@Primary // for multiple beam
public class CricketCoach implements Coach{
    @Override
    public String getDailyWorkOut() {
        return "practice bowl for 5 min";
    }
}
