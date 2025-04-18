package com.studentportal.library;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRatingRepository extends JpaRepository<BookRating, Long> {
    Optional<BookRating> findByBookIdAndHallTicket(Long bookId, String hallTicket);
    List<BookRating> findByBookId(Long bookId);
}
