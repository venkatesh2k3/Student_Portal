package com.studentportal.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.studentportal.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

	Student findByUsername(String username);}