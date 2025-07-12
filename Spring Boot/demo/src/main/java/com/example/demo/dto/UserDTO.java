package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor //make constructor without argument
@AllArgsConstructor //make constructor with argument

public class UserDTO {
    private int id;
    private String name;
}


