package com.studentportal.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "student")

public class Student {
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		private String username;
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		private String applicationNumber;
		public Address getAddress() {
			return address;
		}
		public void setAddress(Address address) {
			this.address = address;
		}
		public Education getEducation() {
			return education;
		}
		public void setEducation(Education education) {
			this.education = education;
		}
		public Family getFamily() {
			return family;
		}
		public void setFamily(Family family) {
			this.family = family;
		}
		public FatherDetails getFatherDetails() {
			return fatherDetails;
		}
		public void setFatherDetails(FatherDetails fatherDetails) {
			this.fatherDetails = fatherDetails;
		}
		private String hallTicket;
		public String getHallTicket() {
			return hallTicket;
		}
		public void setHallTicket(String hallTicket) {
			this.hallTicket = hallTicket;
		}
		private String studentName;
	    private String dateOfBirth;
	    private String gender;
	    private String nativeLanguage;
	    private String nativeState;
	    private String bloodGroup;
	    private boolean physicallyChallenged;
	    private String community;
	    private String religion;
	    private String caste;
	    private String nationality;
	    private boolean hosteller;
	    private String aadharNumber;
	    private String mobileNumber;
	    
	    @OneToOne(cascade = CascadeType.ALL)
	    @JoinColumn(name = "address_id", referencedColumnName = "id")
	    private Address address;

	    @OneToOne(cascade = CascadeType.ALL)
	    @JoinColumn(name = "education_id", referencedColumnName = "id")
	    private Education education;

	    @OneToOne(cascade = CascadeType.ALL)
	    @JoinColumn(name = "family_id", referencedColumnName = "id")
	    private Family family;

	    @OneToOne(cascade = CascadeType.ALL)
	    @JoinColumn(name = "father_id", referencedColumnName = "id")
	    private FatherDetails fatherDetails;
	    
	    
	    public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
	    public String getApplicationNumber() {
			return applicationNumber;
		}
		public void setApplicationNumber(String applicationNumber) {
			this.applicationNumber = applicationNumber;
		}
		public String getStudentName() {
			return studentName;
		}
		public void setStudentName(String studentName) {
			this.studentName = studentName;
		}
		public String getDateOfBirth() {
			return dateOfBirth;
		}
		public void setDateOfBirth(String dateOfBirth) {
			this.dateOfBirth = dateOfBirth;
		}
		public String getGender() {
			return gender;
		}
		public void setGender(String gender) {
			this.gender = gender;
		}
		public String getNativeLanguage() {
			return nativeLanguage;
		}
		public void setNativeLanguage(String nativeLanguage) {
			this.nativeLanguage = nativeLanguage;
		}
		public String getNativeState() {
			return nativeState;
		}
		public void setNativeState(String nativeState) {
			this.nativeState = nativeState;
		}
		public String getBloodGroup() {
			return bloodGroup;
		}
		public void setBloodGroup(String bloodGroup) {
			this.bloodGroup = bloodGroup;
		}
		public boolean isPhysicallyChallenged() {
			return physicallyChallenged;
		}
		public void setPhysicallyChallenged(boolean physicallyChallenged) {
			this.physicallyChallenged = physicallyChallenged;
		}
		public String getCommunity() {
			return community;
		}
		public void setCommunity(String community) {
			this.community = community;
		}
		public String getReligion() {
			return religion;
		}
		public void setReligion(String religion) {
			this.religion = religion;
		}
		public String getCaste() {
			return caste;
		}
		public void setCaste(String caste) {
			this.caste = caste;
		}
		public String getNationality() {
			return nationality;
		}
		public void setNationality(String nationality) {
			this.nationality = nationality;
		}
		public boolean isHosteller() {
			return hosteller;
		}
		public void setHosteller(boolean hosteller) {
			this.hosteller = hosteller;
		}
		public String getAadharNumber() {
			return aadharNumber;
		}
		public void setAadharNumber(String aadharNumber) {
			this.aadharNumber = aadharNumber;
		}
		public String getMobileNumber() {
			return mobileNumber;
		}
		public void setMobileNumber(String mobileNumber) {
			this.mobileNumber = mobileNumber;
		}
		
	}


