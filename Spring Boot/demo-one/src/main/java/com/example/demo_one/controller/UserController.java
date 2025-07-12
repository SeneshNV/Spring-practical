package com.example.demo_one.controller;

import com.example.demo_one.dto.UserDTO;
import com.example.demo_one.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping (value = "/api/v1/user")
@CrossOrigin

public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/getUser")
    public String getUser(){
        return "Spring boot";
    }
    @PostMapping("/postUser")
    public UserDTO postUser(@RequestBody UserDTO userDTO){
        return userService.saveUser(userDTO);
    }
    @PutMapping("/updateUser")
    public String updateUser(){
        return "Spring boot - update users";
    }
    @DeleteMapping("/deleteUser")
    public String deleteUser(){
        return "Spring boot - delete users";
    }
}
