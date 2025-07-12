package com.springweb.springWeb.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeContriller {

    @RequestMapping("/")
    public String greet(){
        System.out.println("I am in the Home Controller");
        return "Hello, welcome to the Spring Web Application!";
    }

    @RequestMapping("/about")
    public String about() {
        return "This is the about page of the Spring Web Application.";
    }
}
