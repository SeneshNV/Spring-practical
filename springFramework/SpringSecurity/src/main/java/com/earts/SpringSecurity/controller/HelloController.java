package com.earts.SpringSecurity.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/")
    public String displayMessage(HttpServletRequest httpServletRequest){
        return "Hello Senesh" + httpServletRequest.getSession().getId();
    }
}
