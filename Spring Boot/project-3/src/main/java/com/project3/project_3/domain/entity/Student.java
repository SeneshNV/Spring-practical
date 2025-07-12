package com.project3.project_3.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Student {
    @Id
    private int id;
    private String uname;
    private  String addres;
    private  String email;

}
