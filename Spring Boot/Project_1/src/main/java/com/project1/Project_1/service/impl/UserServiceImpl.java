package com.project1.Project_1.service.impl;

import com.project1.Project_1.dto.ResponseDTO;
import com.project1.Project_1.dto.UserDTO;
import com.project1.Project_1.entity.User;
import com.project1.Project_1.repository.UserRepository;
import com.project1.Project_1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public ResponseEntity<ResponseDTO> saveUser(UserDTO userDTO) {
        User user = new User();
        user.setName(userDTO.getName());
        user.setAge(userDTO.getAge());

        userRepository.save(user);

        ResponseDTO response = ResponseDTO.builder()
                .message("User saved successfully")
                .responseCode(HttpStatus.CREATED)
                .data(userDTO)
                .build();

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
