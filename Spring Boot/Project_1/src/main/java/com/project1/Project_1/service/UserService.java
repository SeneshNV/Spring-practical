package com.project1.Project_1.service;

import com.project1.Project_1.dto.ResponseDTO;
import com.project1.Project_1.dto.UserDTO;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<ResponseDTO> saveUser(UserDTO userDTO);
}
