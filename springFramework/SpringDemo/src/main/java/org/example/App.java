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
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        Dev obj = (Dev) context.getBean(Dev.class);
//        obj.setAge(25);
//        System.out.println("Dev age: " + obj.getAge());
        obj.build();
    }
}
