package com.studentportal.results;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "results")
public class Results {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String batch;  // Example: "2024-2025"

    @Column(nullable = false)
    private String year;  // Example: "4th Year"

    @Column(nullable = false)
    private String semester;  // Example: "SEM-1"

    @Column(columnDefinition = "LONGBLOB")
    private byte[] pdfData;
    
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBatch() {
		return batch;
	}

	public void setBatch(String batch) {
		this.batch = batch;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}

	public byte[] getPdfData() {
		return pdfData;
	}

	public void setPdfData(byte[] pdfData) {
		this.pdfData = pdfData;
	}

    public Results() {}

    public Results(String batch, String year, String semester, byte[] pdfData) {
        this.batch = batch;
        this.year = year;
        this.semester = semester;
        this.pdfData = pdfData;
    }
}
