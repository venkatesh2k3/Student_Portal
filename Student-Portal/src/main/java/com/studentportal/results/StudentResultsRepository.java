package com.studentportal.results;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentResultsRepository extends JpaRepository<StudentResults, Long> {
    List<StudentResults> findByHallTicketAndBatchAndYearAndSemester(String hallTicket, String batch, String year, String semester);
    
    List<StudentResults> findByHallTicket(String hallTicket);

    @Query("SELECT s FROM StudentResults s WHERE s.hallTicket = :hallTicket AND s.batch = :batch AND s.year = :year AND s.semester = :semester AND s.subcode = :subcode")
    StudentResults findByHallTicketAndBatchAndYearAndSemesterAndSubcode(
            @Param("hallTicket") String hallTicket, 
            @Param("batch") String batch, 
            @Param("year") String year, 
            @Param("semester") String semester, 
            @Param("subcode") String subcode
    );
}

