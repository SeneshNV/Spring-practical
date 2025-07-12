package com.augusta.practical3;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Hello {
    @RequestMapping
    public String sayHello() {
        return "Hello, World!";
    }
}
