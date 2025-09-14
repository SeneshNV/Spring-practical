package com.security_jwt.security_jwt.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class Login {

    @GetMapping("/home")
    public String homePage(){
        return "Hello World...!";
    }
}
