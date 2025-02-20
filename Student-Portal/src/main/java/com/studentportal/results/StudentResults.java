package com.studentportal.results;

import jakarta.persistence.*;

@Entity
@Table(name = "student_results")
public class StudentResults {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String hallTicket;  // Student's hall ticket number

    @Column(nullable = false)
    private String batch;  // Example: "2024-2025"

    @Column(nullable = false)
    private String year;  // Example: "4th Year"

    @Column(nullable = false)
    private String semester;  // Example: "SEM-1"

    @Column(nullable = false)
    private String subcode;  // Subject Code (e.g., "R1641011")

    @Column(nullable = false)
    private String subject;  // Subject Name (e.g., "Mathematics")

    @Column(nullable = false)
    private String grade;  // Grade (e.g., "A")

    @Column(nullable = false)
    private String cred;  // Credits (e.g., 3)

   
    public StudentResults() {}

    
    public StudentResults(String hallTicket, String batch, String year, String semester, String subcode, String subject, String grade, String cred) {
        this.hallTicket = hallTicket;
        this.batch = batch;
        this.year = year;
        this.semester = semester;
        this.subcode = subcode;
        this.subject = subject;
        this.grade = grade;
        this.cred = cred;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getSubcode() {
        return subcode;
    }

    public void setSubcode(String subcode) {
        this.subcode = subcode;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getCred() {
        return cred;
    }

    public void setCred(String cred) {
        this.cred = cred;
    }
}
