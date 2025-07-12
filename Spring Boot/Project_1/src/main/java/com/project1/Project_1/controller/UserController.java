package com.project1.Project_1.controller;

import com.project1.Project_1.dto.ResponseDTO;
import com.project1.Project_1.dto.UserDTO;
import com.project1.Project_1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("api/v1/user")
public class UserController {

    @Autowired
    private UserService userService; // Fixed field declaration

    @GetMapping("/get")
    public String testApi() {
        return "Testing get request";
    }

    @PostMapping("/save")
    public ResponseEntity<ResponseDTO> saveUser(@RequestBody UserDTO userDTO) {
        return userService.saveUser(userDTO);
    }
}
