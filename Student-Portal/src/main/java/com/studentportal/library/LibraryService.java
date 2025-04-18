package com.studentportal.library;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LibraryService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookRatingRepository bookRatingRepository;

    public void rateBook(Long bookId, String hallTicket, int rating) {
        Optional<BookRating> existingRating = bookRatingRepository.findByBookIdAndHallTicket(bookId, hallTicket);
        Book book = bookRepository.findById(bookId).orElse(null);

        if (book == null) return;

        if (existingRating.isPresent()) {
            BookRating br = existingRating.get();
            br.setRating(rating);
            bookRatingRepository.save(br);
        } else {
            BookRating newRating = new BookRating(bookId, hallTicket, rating);
            bookRatingRepository.save(newRating);

            // ✅ Increment totalRatings
            book.setTotalRatings(book.getTotalRatings() + 1);
            bookRepository.save(book);
        }
    }

    public void addBook(Book book) {
        bookRepository.save(book);
    }

    public List<Book> getBooksSortedByRating() {
        List<Book> books = bookRepository.findAll();
        Map<Long, Double> avgRatings = new HashMap<>();

        for (Book book : books) {
            List<BookRating> ratings = bookRatingRepository.findByBookId(book.getId());
            double avg = ratings.stream().mapToInt(BookRating::getRating).average().orElse(0);
            avgRatings.put(book.getId(), avg);
        }

        return books.stream()
                .sorted((b1, b2) -> Double.compare(avgRatings.getOrDefault(b2.getId(), 0.0),
                                                   avgRatings.getOrDefault(b1.getId(), 0.0)))
                .peek(book -> {
                    double rating = avgRatings.getOrDefault(book.getId(), 0.0);
                    if (rating >= 4.5 && book.getPublishedYear() >= 2023) {
                        book.setTag("Best Book 🌟");
                    } else {
                        book.setTag(null);
                    }
                })
                .collect(Collectors.toList());
    }
}