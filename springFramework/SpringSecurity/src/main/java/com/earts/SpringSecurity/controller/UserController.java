package com.earts.SpringSecurity.controller;

import com.earts.SpringSecurity.model.Users;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @PostMapping("/signup")
    public Users register(@RequestBody Users user){
        return user;
    }
}
