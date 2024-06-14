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
        alien1.age = 2;
        Alien alien2 = (Alien) context.getBean("alien");
        alien2.code();
        Alien alien3 = (Alien) context.getBean("alienNonMutable");
        alien3.age = 3;
        alien3.code();
        Alien alien4 = (Alien) context.getBean("alienNonMutable");
        alien4.code();
    }
}
