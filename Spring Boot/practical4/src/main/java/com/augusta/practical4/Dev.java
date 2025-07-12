package com.augusta.practical4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Dev {

    @Autowired // field injection
    @Qualifier("desktop") // Qualifier annotation to specify which bean to inject
    Computer computer;

//    public Dev(Laptop laptop) { // Constructor injection (Default)
//        this.laptop = laptop;
//    }

//    @Autowired
//    public void setLaptop(Laptop laptop) { // Setter injection
//        this.laptop = laptop;
//    }

    public void build2(){
        computer.compile();
        System.out.println("😊Working on some projects");
    }
}



