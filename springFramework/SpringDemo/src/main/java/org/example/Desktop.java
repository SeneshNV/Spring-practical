package org.example;

public class Desktop implements Computer{
    public Desktop() {
        System.out.println("Desktop constructor called...!");
    }

    public void compile() {
        System.out.println("Desktop is compiling code...");
    }
}
