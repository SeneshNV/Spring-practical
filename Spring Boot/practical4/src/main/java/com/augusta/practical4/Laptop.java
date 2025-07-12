package com.augusta.practical4;

import org.springframework.stereotype.Component;

@Component
public class Laptop implements Computer{

    public void compile() {
        System.out.println("Compiling the code... from Laptop");
    }
}
