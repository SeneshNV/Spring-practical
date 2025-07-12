package com.project1.Project_1.repository;

import com.project1.Project_1.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository //label as a repository class
public interface RUserRepository extends JpaRepository<User, Integer> {
}
