package com.studentportal.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.studentportal.model.Family;

public interface FamilyRepository extends JpaRepository<Family, Long> {}