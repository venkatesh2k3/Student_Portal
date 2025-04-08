package com.studentportal.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "family")
public class Family {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int numberOfBrothers;
    private int numberOfSisters;
    private boolean hasSiblingInVikas;
    private String siblingStudyingDetails;
    private boolean hasSiblingStudiedInVikas;
    private String siblingStudiedDetails;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getNumberOfBrothers() {
		return numberOfBrothers;
	}
	public void setNumberOfBrothers(int numberOfBrothers) {
		this.numberOfBrothers = numberOfBrothers;
	}
	public int getNumberOfSisters() {
		return numberOfSisters;
	}
	public void setNumberOfSisters(int numberOfSisters) {
		this.numberOfSisters = numberOfSisters;
	}
	public boolean isHasSiblingInVikas() {
		return hasSiblingInVikas;
	}
	public void setHasSiblingInVikas(boolean hasSiblingInVikas) {
		this.hasSiblingInVikas = hasSiblingInVikas;
	}
	public String getSiblingStudyingDetails() {
		return siblingStudyingDetails;
	}
	public void setSiblingStudyingDetails(String siblingStudyingDetails) {
		this.siblingStudyingDetails = siblingStudyingDetails;
	}
	public boolean isHasSiblingStudiedInVikas() {
		return hasSiblingStudiedInVikas;
	}
	public void setHasSiblingStudiedInVikas(boolean hasSiblingStudiedInVikas) {
		this.hasSiblingStudiedInVikas = hasSiblingStudiedInVikas;
	}
	public String getSiblingStudiedDetails() {
		return siblingStudiedDetails;
	}
	public void setSiblingStudiedDetails(String siblingStudiedDetails) {
		this.siblingStudiedDetails = siblingStudiedDetails;
	}

}

