package com.second.Second.project.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RequestMapping("/api/v1/example")
@RestController
public class ExampleController {
    @GetMapping("/get")
    public String test(){
        return "Hello SpringBoot";
    }
}
