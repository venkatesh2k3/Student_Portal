package com.studentportal.results;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ResultsService {

    private final ResultsRepository resultsRepository;
    private final StudentResultsRepository studentResultsRepository;

    public ResultsService(ResultsRepository resultsRepository, StudentResultsRepository studentResultsRepository) {
        this.resultsRepository = resultsRepository;
        this.studentResultsRepository = studentResultsRepository;
    }

    // ✅ Store PDF and Extract Data
    public void processResultsPDF(MultipartFile file, String batch, String year, String semester) {
        try {
            byte[] pdfBytes = file.getBytes();

            // Store PDF in DB
            Results results = new Results(batch, year, semester, pdfBytes);
            resultsRepository.save(results);

            // Extract and store student results from PDF
            extractStudentResults(pdfBytes, batch, year, semester);

        } catch (IOException e) {
            throw new RuntimeException("Error processing results PDF", e);
        }
    }

    // ✅ Extracts Student Results from PDF and Stores in DB
    private void extractStudentResults(byte[] pdfBytes, String batch, String year, String semester) {
        try (PDDocument document = PDDocument.load(pdfBytes)) {
            PDFTextStripper pdfStripper = new PDFTextStripper();
            String text = pdfStripper.getText(document);

            String[] lines = text.split("\n");

            for (String line : lines) {
                String[] parts = line.split("\\s+");

                if (parts.length >= 6) { // Ensure we have all required fields
                    String hallTicket = parts[1];   // Example: "19NQ5A0104"
                    String subcode = parts[2];      // Example: "R1641011"
                    String subname = extractSubjectName(parts); // Extract full subject name
                    String grade = parts[parts.length - 2];  // Example: "A"
                    String credits = parts[parts.length - 1]; // Example: "3"

                    // Check if the result already exists
                    StudentResults existingResult = studentResultsRepository.findByHallTicketAndBatchAndYearAndSemesterAndSubcode(
                            hallTicket, batch, year, semester, subcode
                    );

                    if (existingResult != null) {
                        // Update existing result
                        existingResult.setGrade(grade);
                        existingResult.setCred(credits);

                        studentResultsRepository.save(existingResult);
                    } else {
                        // Insert new record
                        StudentResults studentResult = new StudentResults(hallTicket, batch, year, semester, subcode, subname, grade, credits);
                        studentResultsRepository.save(studentResult);
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Error extracting data from PDF", e);
        }
    }

    // ✅ Compute Student Performance
    public StudentPerformance calculateStudentPerformance(String hallTicket, String batch, String year, String semester) {
        List<StudentResults> results = studentResultsRepository.findByHallTicketAndBatchAndYearAndSemester(hallTicket, batch, year, semester);

        int totalSubjects = results.size();
        int passedSubjects = 0;
        int failedSubjects = 0;
        double totalGradePoints = 0;
        int totalCredits = 0;
        List<String> failedSubjectsList = new ArrayList<>();

        for (StudentResults result : results) {
            int credits = Integer.parseInt(result.getCred());
            totalCredits += credits;

            int gradePoint = convertGradeToPoints(result.getGrade());
            totalGradePoints += gradePoint * credits;

            if (gradePoint > 0) {
                passedSubjects++;
            } else {
                failedSubjects++;
                failedSubjectsList.add(result.getSubject());
            }
        }

        double gpa = (totalCredits == 0) ? 0 : totalGradePoints / totalCredits;
        double passPercentage = (totalSubjects == 0) ? 0 : (passedSubjects * 100.0) / totalSubjects;

        return new StudentPerformance(hallTicket, batch, year, semester, gpa, passPercentage, totalSubjects, passedSubjects, failedSubjects, failedSubjectsList);
    }

    // ✅ Converts Grade (A, B, C...) to Points
    private int convertGradeToPoints(String grade) {
        switch (grade.toUpperCase()) {
            case "A+": return 10;
            case "A": return 9;
            case "B": return 8;
            case "C": return 7;
            case "D": return 6;
            case "F": return 0;  // Fail
            case "ABSENT": return 0;
            default: return 0;
        }
    }

    // ✅ Extracts Full Subject Name
    private String extractSubjectName(String[] parts) {
        StringBuilder subjectName = new StringBuilder();
        for (int i = 3; i < parts.length - 2; i++) { // Avoid last three columns (Internals, Grade, Credits)
            subjectName.append(parts[i]).append(" ");
        }
        return subjectName.toString().trim();
    }

    // ✅ Fetch Student Results
    public List<StudentResults> getResults(String hallTicket, String batch, String year, String semester) {
        return studentResultsRepository.findByHallTicketAndBatchAndYearAndSemester(hallTicket, batch, year, semester);
    }
}
