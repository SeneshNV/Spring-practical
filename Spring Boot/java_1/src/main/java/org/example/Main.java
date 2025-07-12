package org.example;

import java.util.Scanner;

public class Main {
    public static String name(String input){
        StringBuilder name = new StringBuilder(input);
        return name.reverse().toString();
    }
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter test : ");
        String input = scanner.nextLine();

        String newInput = name(input);
        System.out.println(newInput);
    }
}