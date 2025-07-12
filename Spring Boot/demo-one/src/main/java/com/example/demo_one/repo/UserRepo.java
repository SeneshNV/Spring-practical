package com.example.demo_one.repo;

import com.example.demo_one.entity.User;


import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepo extends JpaRepository<User, Integer> {


}
