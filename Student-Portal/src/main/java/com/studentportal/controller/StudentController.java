package com.studentportal.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.studentportal.model.Student;
import com.studentportal.model.User;
import com.studentportal.service.StudentService;
import com.studentportal.util.JwtUtil;
import com.studentportal.repo.UserRepository;

@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private JwtUtil jwtUtil;  // Ensure JwtUtil is injected

    @Autowired
    private UserRepository userRepository;  // Inject UserRepository to check roles

    // Endpoint to get student details based on JWT token
    @GetMapping("/student/details")
    public Student getStudentDetails(@RequestHeader("Authorization") String token) {
        // Remove the "Bearer " prefix if present in the token
        String jwtToken = token.startsWith("Bearer ") ? token.substring(7) : token;

        return studentService.getStudentDetails(jwtToken);  // Use the jwt token to fetch student details
    }

    // Endpoint to register a student (only accessible by admin)
    @PostMapping("/register")
    public Student registerStudent(@RequestBody Student student, @RequestHeader("Authorization") String token) {
        // Extract and verify admin from JWT token
        String jwtToken = token.startsWith("Bearer ") ? token.substring(7) : token;
        String username = jwtUtil.extractUsername(jwtToken);  // Get the username from the JWT token

        Optional<User> userOpt = userRepository.findByUsername(username);  // Fetch the user from the database

        if (userOpt.isPresent() && userOpt.get().getRole().equalsIgnoreCase("ADMIN")) {

            // If user is an admin, allow registration of student
            return studentService.registerStudent(student);
        }

        // If not an admin, throw an Unauthorized exception
        throw new RuntimeException("Unauthorized: Admin role required");
    }
}
