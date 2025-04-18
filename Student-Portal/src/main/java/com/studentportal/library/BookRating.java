package com.studentportal.library;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class BookRating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long bookId;
    private String hallTicket;
    private int rating;

    public BookRating() {}

    public BookRating(Long bookId, String hallTicket, int rating) {
        this.bookId = bookId;
        this.hallTicket = hallTicket;
        this.rating = rating;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getBookId() {
		return bookId;
	}

	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}

	public String getHallTicket() {
		return hallTicket;
	}

	public void setHallTicket(String hallTicket) {
		this.hallTicket = hallTicket;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

    
}
