package com.example.demo.common;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class BaseBallCoach implements Coach{
    public String getDailyWorkOut() {
        return "30 min in baseball";
    }

    // define init method
    @PostConstruct
    public void doMyStartStuff() {
        System.out.println("init");
    }

    // define init method
    @PreDestroy
    public void doCleanUpStuff() {
        System.out.println("destroy");
    }
}
