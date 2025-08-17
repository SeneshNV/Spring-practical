package com.jwt.jwt_app.controller;

import com.jwt.jwt_app.service.JWTService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HomeController {

    private final JWTService jwtService;

    @GetMapping("/")
    public String getHello(){
        return "Senesh NV";
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody String username) {
        String token = jwtService.getJwtToken(username);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

}
