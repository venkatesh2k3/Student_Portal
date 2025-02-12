package com.studentportal.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "fatherDetails")
public class FatherDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fatherName;
    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFatherName() {
		return fatherName;
	}
	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}
	public String getQualification() {
		return qualification;
	}
	public void setQualification(String qualification) {
		this.qualification = qualification;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	public String getOrganization() {
		return organization;
	}
	public void setOrganization(String organization) {
		this.organization = organization;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAnnualIncome() {
		return annualIncome;
	}
	public void setAnnualIncome(String annualIncome) {
		this.annualIncome = annualIncome;
	}
	public String getOfficialAddress() {
		return officialAddress;
	}
	public void setOfficialAddress(String officialAddress) {
		this.officialAddress = officialAddress;
	}
	public String getOfficialState() {
		return officialState;
	}
	public void setOfficialState(String officialState) {
		this.officialState = officialState;
	}
	public String getOfficialCity() {
		return officialCity;
	}
	public void setOfficialCity(String officialCity) {
		this.officialCity = officialCity;
	}
	public String getOfficialPincode() {
		return officialPincode;
	}
	public void setOfficialPincode(String officialPincode) {
		this.officialPincode = officialPincode;
	}
	public String getOfficialPhoneNumber() {
		return officialPhoneNumber;
	}
	public void setOfficialPhoneNumber(String officialPhoneNumber) {
		this.officialPhoneNumber = officialPhoneNumber;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	private String qualification;
    private String occupation;
    private String organization;
    private String employeeId; // If employed in VCTN
    private String mobileNumber;
    private String email;
    private String annualIncome;
    private String officialAddress;
    private String officialState;
    private String officialCity;
    private String officialPincode;
    private String officialPhoneNumber;
    private String designation;

    
}
