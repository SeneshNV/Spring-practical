package com.project2.Project_2.service;

import com.project2.Project_2.dto.UserDTO;
import com.project2.Project_2.entity.User;
import com.project2.Project_2.repo.UserRepo;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional //data translation
public class UserService {
    @Autowired //dependency inject
    private UserRepo userRepo; //make object in UserRepo

    @Autowired
    private ModelMapper modelMapper;

    public List<UserDTO> getAllUsers(){
        List<User>UserList = userRepo.findAll();
        return modelMapper.map(UserList, new TypeToken<List<UserDTO>>(){}.getType());
    }

}
