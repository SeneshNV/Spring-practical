package com.springweb.springWeb.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {


    @RequestMapping("/login")
    public String login() {
        System.out.println("I am in the Login Controller");
        return "login Page";
    }
}
