package com.augasta.protectyou.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/jwt")
public class HomeController {

    @GetMapping("/home")
    public String getHome(){
        Integer age = 22;
        age.byteValue();
        System.out.println(age);
        return age.toString();
    }

    @PostMapping("/login")
    public String postLogin(){
        return "Testing POST request...";
    }

    @PostMapping("/signup")
    public String postSignup(){
        return "Testing POST request...";
    }
}
