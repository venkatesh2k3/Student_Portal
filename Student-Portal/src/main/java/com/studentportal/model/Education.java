package com.studentportal.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "education")
public class Education {
    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getAppliedDegree() {
		return appliedDegree;
	}
	public void setAppliedDegree(String appliedDegree) {
		this.appliedDegree = appliedDegree;
	}
	public String getEducationalQualification() {
		return educationalQualification;
	}
	public void setEducationalQualification(String educationalQualification) {
		this.educationalQualification = educationalQualification;
	}
	public String getBranchStudied() {
		return branchStudied;
	}
	public void setBranchStudied(String branchStudied) {
		this.branchStudied = branchStudied;
	}
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	public String getMediumOfStudy() {
		return mediumOfStudy;
	}
	public void setMediumOfStudy(String mediumOfStudy) {
		this.mediumOfStudy = mediumOfStudy;
	}
	public String getBoardOrUniversity() {
		return boardOrUniversity;
	}
	public void setBoardOrUniversity(String boardOrUniversity) {
		this.boardOrUniversity = boardOrUniversity;
	}
	public String getRegisterNumber() {
		return registerNumber;
	}
	public void setRegisterNumber(String registerNumber) {
		this.registerNumber = registerNumber;
	}
	public String getClassObtained() {
		return classObtained;
	}
	public void setClassObtained(String classObtained) {
		this.classObtained = classObtained;
	}
	public String getYearOfPassing() {
		return yearOfPassing;
	}
	public void setYearOfPassing(String yearOfPassing) {
		this.yearOfPassing = yearOfPassing;
	}
	public String getMonthOfPassing() {
		return monthOfPassing;
	}
	public void setMonthOfPassing(String monthOfPassing) {
		this.monthOfPassing = monthOfPassing;
	}
	public String getSchoolAddress() {
		return schoolAddress;
	}
	public void setSchoolAddress(String schoolAddress) {
		this.schoolAddress = schoolAddress;
	}
	public String getSchoolAreaName() {
		return schoolAreaName;
	}
	public void setSchoolAreaName(String schoolAreaName) {
		this.schoolAreaName = schoolAreaName;
	}
	public String getSchoolCity() {
		return schoolCity;
	}
	public void setSchoolCity(String schoolCity) {
		this.schoolCity = schoolCity;
	}
	public String getSchoolState() {
		return schoolState;
	}
	public void setSchoolState(String schoolState) {
		this.schoolState = schoolState;
	}
	public String getSchoolPincode() {
		return schoolPincode;
	}
	public void setSchoolPincode(String schoolPincode) {
		this.schoolPincode = schoolPincode;
	}
	public String getSchoolPhoneNumber() {
		return schoolPhoneNumber;
	}
	public void setSchoolPhoneNumber(String schoolPhoneNumber) {
		this.schoolPhoneNumber = schoolPhoneNumber;
	}
	public boolean isBreakInStudy() {
		return breakInStudy;
	}
	public void setBreakInStudy(boolean breakInStudy) {
		this.breakInStudy = breakInStudy;
	}
	public String getReasonForBreak() {
		return reasonForBreak;
	}
	public void setReasonForBreak(String reasonForBreak) {
		this.reasonForBreak = reasonForBreak;
	}
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String appliedDegree;
    private String educationalQualification;
    private String branchStudied;
    private String schoolName;
    private String mediumOfStudy;
    private String boardOrUniversity;
    private String registerNumber;
    private String classObtained;
    private String yearOfPassing;
    private String monthOfPassing;
    private String schoolAddress;
    private String schoolAreaName;
    private String schoolCity;
    private String schoolState;
    private String schoolPincode;
    private String schoolPhoneNumber;
    private boolean breakInStudy;
    private String reasonForBreak;

   
}
