package com.earts.SpringSecurity.controller;

import com.earts.SpringSecurity.model.Student;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {

    private static final Logger log = LoggerFactory.getLogger(StudentController.class);

    private List<Student> students = new ArrayList<>(List.of(
            new Student(1, "Senesh", 89),
            new Student(2, "Dilshan", 78),
            new Student(3, "Chanduma", 88)
    ));

    @GetMapping("/all-students")
    public ResponseEntity<?> allStudents(){
        log.info("get request in pointed....");
        List<Student> st1 = students;
        return new ResponseEntity<>(st1, HttpStatus.OK);
    }

    @GetMapping("/students")
    public String getStudents(){
        Student student1 = new Student();
        return student1.toString();
    }

    @PostMapping("/students")
    public ResponseEntity<?> addStudent(@RequestBody Student student){
        System.out.println("post request in pointed....");
        Student student1 = new Student();
        log.info("post request in pointed....");
        student1.setId(student.getId());
        student1.setName(student.getName());
        student1.setMarks(student.getMarks());

        return new ResponseEntity<>(student1.toString(), HttpStatus.CREATED);
    }

    @GetMapping("/csrf")
    public CsrfToken getCsrfToken(HttpServletRequest request){
        return (CsrfToken) request.getAttribute("_csrf");
    }

    @PostMapping("/postStudent")
    public List<Student> postStudent(@RequestBody Student student){
        students.add(student);
        return students;
    }
}
