package com.studentportal.service;

import java.util.Collections;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.studentportal.model.Student;
import com.studentportal.model.User;
import com.studentportal.repo.UserRepository;
import com.studentportal.repo.StudentRepository;
import com.studentportal.util.JwtUtil;

@Service
public class AuthService {

    private static final String ROLE_STUDENT = "STUDENT";
    private static final String ROLE_EMPLOYEE = "EMPLOYEE";
    private static final String ROLE_ADMIN = "ADMIN";

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Map<String, String> login(Map<String, String> request, String role) {
        String username = request.get("username");
        String password = request.get("password");

        // Authenticate username and password
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(username, password)
        );

        // Find the user in the database
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        // Check if the user's role matches the expected role
        if (!user.getRole().equalsIgnoreCase(role)) {
            throw new AccessDeniedException("Unauthorized access");
        }

        // Initialize UserDetails with proper authorities
        GrantedAuthority authority = new SimpleGrantedAuthority(user.getRole());
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(), Collections.singleton(authority));

        // Generate JWT token
        String token = jwtUtil.generateToken(userDetails.getUsername(), user.getRole());

        // Return the token and additional information
        return Map.of(
            "jwt", token,
            "username", user.getUsername(),
            "role", user.getRole()
        );
    }

    // Method to get logged-in user's details
    private User getLoggedInUserDetails() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    // Method to get the personal details of the student
    public Student getStudentPersonalDetails() {
        User user = getLoggedInUserDetails();
        return studentRepository.findByUsername(user.getUsername());  // Fetch student details based on username
    }

    public Map<String, String> registerUser(Map<String, String> request) {
        String username = request.get("username");
        String password = request.get("password");
        String email = request.get("email");
        String role = request.get("role"); // e.g., "STUDENT" or "EMPLOYEE"

        // Check if the username or email already exists
        if (userRepository.existsByUsername(username)) {
            throw new IllegalArgumentException("Username already exists");
        }
        if (userRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("Email already exists");
        }

        // Encode the password
        String encodedPassword = passwordEncoder.encode(password);

        // Create a new User object
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(encodedPassword);
        newUser.setEmail(email);
        newUser.setRole(role);

        // Save the user in the database
        userRepository.save(newUser);

        // Return a success message
        return Map.of("message", "User registered successfully");
    }
}