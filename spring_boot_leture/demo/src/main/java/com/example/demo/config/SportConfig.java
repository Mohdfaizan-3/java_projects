package com.example.demo.config;

import com.example.demo.common.Coach;
import com.example.demo.common.SwimCoach;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SportConfig {
    @Bean("aquatic")
    public Coach swimCoach() {
        return new SwimCoach();
    }
}

/*
Bean - It is mainly used in 3rd party class to inject as bean class.
 Since we have not access to 3rd party class we cannot make it component
The @Bean annotation in Spring is used to indicate that a method instantiates, configures,
and initializes a new object to be managed by the Spring IoC container.
It’s used in conjunction with
@Configuration classes where method-level annotations tell Spring that the method
will return an object that should be registered as a bean in the Spring application context.
 */
