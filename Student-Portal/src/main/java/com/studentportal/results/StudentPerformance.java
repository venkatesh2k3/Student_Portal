package com.studentportal.results;

import java.util.List;

public class StudentPerformance {
	private String hallTicket;
	private String batch;
	private String year;
	private String semester;
	private double gpa;
	private double passPercentage;
	private int totalSubjects;
	private int passedSubjects;
	private int failedSubjects;
	private List<String> failedSubjectsList;

	public StudentPerformance(String hallTicket, String batch, String year, String semester, double gpa,
			double passPercentage, int totalSubjects, int passedSubjects, int failedSubjects,
			List<String> failedSubjectsList) {
		this.hallTicket = hallTicket;
		this.batch = batch;
		this.year = year;
		this.semester = semester;
		this.gpa = gpa;
		this.passPercentage = passPercentage;
		this.totalSubjects = totalSubjects;
		this.passedSubjects = passedSubjects;
		this.failedSubjects = failedSubjects;
		this.failedSubjectsList = failedSubjectsList;
	}

	public String getHallTicket() {
		return hallTicket;
	}

	public void setHallTicket(String hallTicket) {
		this.hallTicket = hallTicket;
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

	public double getGpa() {
		return gpa;
	}

	public void setGpa(double gpa) {
		this.gpa = gpa;
	}

	public double getPassPercentage() {
		return passPercentage;
	}

	public void setPassPercentage(double passPercentage) {
		this.passPercentage = passPercentage;
	}

	public int getTotalSubjects() {
		return totalSubjects;
	}

	public void setTotalSubjects(int totalSubjects) {
		this.totalSubjects = totalSubjects;
	}

	public int getPassedSubjects() {
		return passedSubjects;
	}

	public void setPassedSubjects(int passedSubjects) {
		this.passedSubjects = passedSubjects;
	}

	public int getFailedSubjects() {
		return failedSubjects;
	}

	public void setFailedSubjects(int failedSubjects) {
		this.failedSubjects = failedSubjects;
	}

	public List<String> getFailedSubjectsList() {
		return failedSubjectsList;
	}

	public void setFailedSubjectsList(List<String> failedSubjectsList) {
		this.failedSubjectsList = failedSubjectsList;
	}

}
