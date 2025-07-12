package com.project1.Project_1.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity // to converte this as a table in database
@Table(name = "user_table") //customize table name
@Data //lombok (getters, setter include this
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id //set as a id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto increment
    private int id;
    @Column(name = "user_name", length = 150) //customize table column
    private String name;
    @Column(name = "user_age")
    private int age;
}
