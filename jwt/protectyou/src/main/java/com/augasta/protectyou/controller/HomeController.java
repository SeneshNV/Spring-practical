package com.augasta.protectyou.controller;

import com.augasta.protectyou.DTO.LoginRequest;
import com.augasta.protectyou.service.JWTService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/jwt")
@AllArgsConstructor
public class HomeController {

    private final JWTService jwtService;

    @GetMapping("/home")
    public String getHome(){
        Integer age = 22;
        age.byteValue();
        System.out.println(age);
        return age.toString();
    }

    @PostMapping("/login")
    public String postLogin(@RequestBody LoginRequest userInfo ){
        String token = jwtService.generateToken(userInfo);
        return token;
    }

    @GetMapping("/username")
    public String getUsername(@RequestHeader("Authorization2") String authHeader) {
        String token = authHeader.replace("Bearer ", "").trim();
        return jwtService.getUsername(token);
    }

    @PostMapping("/signup")
    public String postSignup(){
        return "Testing POST request...";
    }
}
