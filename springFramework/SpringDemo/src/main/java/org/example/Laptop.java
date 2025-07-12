package org.example;

public class Laptop implements Computer{
    public Laptop(){
        System.out.println("Laptop constructor called...!");
    }



    public void compile() {
        System.out.println("Laptop is compiling code...");
    }
}
