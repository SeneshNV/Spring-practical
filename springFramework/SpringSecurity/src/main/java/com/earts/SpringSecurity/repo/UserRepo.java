package com.earts.SpringSecurity.repo;

import com.earts.SpringSecurity.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<Users, Integer> {
    Users findByUsername(String username);
}
