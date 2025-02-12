package com.studentportal.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.studentportal.model.FatherDetails;

public interface FatherDetailsRepository extends JpaRepository<FatherDetails, Long> {
	
}

