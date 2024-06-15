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
    //Thus, although initialization lifecycle callback methods are called on all objects regardless of scope,
    // in the case of prototypes, configured destruction lifecycle callbacks are not called.
    //The client code must clean up prototype-scoped objects and release expensive resources
    // that the prototype bean(s) are holding.
    @PreDestroy
    public void doCleanUpStuff() {
        System.out.println("destroy");
    }
}
