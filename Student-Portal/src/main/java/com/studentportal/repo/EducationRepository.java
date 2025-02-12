package com.studentportal.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.studentportal.model.Education;

public interface EducationRepository extends JpaRepository<Education, Long> {}