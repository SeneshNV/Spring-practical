package org.example;

public class Main {
    public static void main(String[] args) {
        Employee e1 = new Employee("Senesh", 26);
        System.out.println(e1.getName());
        System.out.println(e1.getAge());

        //update
        e1.setAge(10);
        System.out.println("new age is : " + e1.getAge());
    }
}