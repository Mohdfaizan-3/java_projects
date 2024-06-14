package javaConfig;

import org.example.Desktop;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class javaConfig {
//    @Bean(name = {"beast", "desktop"}) here we can specfied bean name
    @Bean// default bean name destop()
    public Desktop desktop() {
        return new Desktop();
    }
}
