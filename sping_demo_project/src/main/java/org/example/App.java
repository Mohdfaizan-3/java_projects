package org.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");//get mvn dependency "spring context"
        Alien alien1 = (Alien)  context.getBean("alien");// create a spring.xml file in resource folder and add beam
        alien1.code();
    }
}
