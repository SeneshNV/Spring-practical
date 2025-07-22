package com.earts.SpringSecurity.controller;

import org.springframework.web.bind.annotation.RequestMapping;

public class HelloController {
    @RequestMapping("/")
    public String displayMessage(){
        return "Hello Senesh";
    }
}
