package javaConfig;

import org.example.Alien;
import org.example.Computer;
import org.example.Desktop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class javaConfig {
//    @Bean(name = {"beast", "desktop"}) here we can specfied bean name
    @Bean// default bean name destop()
    @Scope("prototype")
    public Desktop desktop() {
        return new Desktop();
    }

    @Bean
    public Alien alien(@Autowired Computer com) {
        Alien obj = new Alien();
        obj.setCom(com);
        return obj;
    }
}
