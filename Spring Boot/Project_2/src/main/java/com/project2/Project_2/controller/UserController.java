package com.project2.Project_2.controller;

import com.project2.Project_2.dto.UserDTO;
import com.project2.Project_2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "api/v1/user")
public class UserController {
    @Autowired
    private UserService userService;

     @GetMapping("/get")
    public List<UserDTO> getUser(){
         return userService.getAllUsers();
     }
}
