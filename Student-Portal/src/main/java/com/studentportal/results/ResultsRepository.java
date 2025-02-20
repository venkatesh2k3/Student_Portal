package com.studentportal.results;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResultsRepository extends JpaRepository<Results, Long> {
    Optional<Results> findByBatchAndYearAndSemester(String batch, String year, String semester);
}
