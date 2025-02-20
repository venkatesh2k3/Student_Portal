package com.studentportal.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.studentportal.model.Student;
import com.studentportal.service.AuthService;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;
    
    
    @PostMapping("/login/admin")
    public Map<String, String> loginAdmin(@RequestBody Map<String, String> request) {
        return authService.login(request, "ADMIN");
    }

    @PostMapping("/login/student")
    public Map<String, String> loginStudent(@RequestBody Map<String, String> request) {
        return authService.login(request, "STUDENT");
    }

    @PostMapping("/login/employee")
    public Map<String, String> loginEmployee(@RequestBody Map<String, String> request) {
        return authService.login(request, "EMPLOYEE");
    }
    
    @PostMapping("/register")
    public Map<String, String> registerUser(@RequestBody Map<String, String> request) {
        return authService.registerUser(request);
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello";
    }

    // New endpoint to fetch logged-in student's personal details
    
//    @GetMapping("/student/details")
//    public Student getStudentDetails() {
//        return authService.getStudentPersonalDetails();  // Fetch and return the logged-in student's details
//    }
}
