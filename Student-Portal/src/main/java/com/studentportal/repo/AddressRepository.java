package com.studentportal.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.studentportal.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {}