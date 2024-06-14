package org.example;

import javaConfig.javaConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        // using java based config
        ApplicationContext context = new AnnotationConfigApplicationContext(javaConfig.class);

        Computer destop = context.getBean("desktop", Desktop.class); // by using bean name here desktop is desktop() in javaconfig class
        destop.working();

//        Computer desktop = context.getBean(Desktop.class);// here we are specifying the type desktop
//        desktop.working();







        // using XML based config
       // ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");//get mvn dependency "spring context"

        // no need to type cast
//            Computer com = context.getBean(Desktop.class);
//            com.working();

        // no need to type cast
//        Computer com = context.getBean("desktop", Desktop.class);
//        com.working();

        // constructor injection
//        Alien alien = (Alien) context.getBean("alienNonMutable");
//        System.out.println(alien.getAge());
//        System.out.println(alien.getSalary());
//        System.out.println(alien.getLap());

//        alien.setAge(4);
//        alien.setSalary(10000);
//        System.out.println(alien.getAge());
//        System.out.println(alien.getSalary());
//        System.out.println(alien.getLap());





//setter and ref
//        Alien alien1 = (Alien)  context.getBean("alienNonMutable");// create a spring.xml file in resource folder and add beam
//        System.out.println(alien1.getAge());//1 setter injection
//        alien1.getLap().working();//laptop// ref attribute




        // singleton vs prototype
//        Alien alien1 = (Alien)  context.getBean("alien");// create a spring.xml file in resource folder and add beam
//        alien1.code();//1
//        alien1.age = 2;
//        Alien alien2 = (Alien) context.getBean("alien");
//        alien2.code();//1
//        Alien alien3 = (Alien) context.getBean("alienNonMutable");
//        alien3.age = 3;
//        alien3.code();//3
//        Alien alien4 = (Alien) context.getBean("alienNonMutable");
//        alien4.code();//1
    }
}
