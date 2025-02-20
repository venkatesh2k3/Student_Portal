package com.studentportal.results;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentResultsRepository extends JpaRepository<StudentResults, Long> {
    List<StudentResults> findByHallTicketAndBatchAndYearAndSemester(String hallTicket, String batch, String year, String semester);
    
    List<StudentResults> findByHallTicket(String hallTicket);


}

