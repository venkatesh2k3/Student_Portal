package com.studentportal.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studentportal.model.Student;
import com.studentportal.model.User;
import com.studentportal.repo.StudentRepository;
import com.studentportal.repo.UserRepository;
import com.studentportal.util.JwtUtil;

@Service
public class StudentService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private JwtUtil jwtUtil;

    // Method to get student details based on JWT token
    public Student getStudentDetails(String token) {
        // Extract username from the JWT token
        String username = jwtUtil.extractUsername(token);

        // Fetch user by username
        Optional<User> userOpt = userRepository.findByUsername(username);

        if (userOpt.isPresent()) {
            // If user exists, fetch and return student details
            return studentRepository.findByUsername(username);  // Assuming you have this method in the StudentRepository
        }

        // If user is not found, handle as per your requirement (throw exception, return null, etc.)
        throw new RuntimeException("User not found");
    }
    public Student registerStudent(Student student) {
        return studentRepository.save(student);  // Save the student to the database
    }
}

