package com.earts.SpringSecurity.controller;

import com.earts.SpringSecurity.model.Users;
import com.earts.SpringSecurity.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public Users register(@RequestBody Users user){
        Users user1 = userService.registerUser(user);
        return user1;
    }

    @PostMapping("/login")
    public String login(@RequestBody Users user){
        String message = userService.verify(user);
        return message;
    }
}
