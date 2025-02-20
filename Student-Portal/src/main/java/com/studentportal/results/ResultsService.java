package com.studentportal.results;

import java.io.IOException;
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

    // Store PDF and Extract Data
    public void processResultsPDF(MultipartFile file, String batch, String year, String semester) {
        try {
            byte[] pdfBytes = file.getBytes();

            // Store PDF in the database
            Results results = new Results(batch, year, semester, pdfBytes);
            resultsRepository.save(results);

            // Extract and store student results from PDF
            extractStudentResults(pdfBytes, batch, year, semester);

        } catch (IOException e) {
            throw new RuntimeException("Error processing results PDF", e);
        }
    }

    //  Extracts Student Results from PDF and Stores in DB
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
                    String cred = parts[parts.length - 1]; // Example: "3"

                    StudentResults studentResult = new StudentResults(hallTicket, batch, year, semester, subcode, subname, grade, cred);
                    studentResultsRepository.save(studentResult);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Error extracting data from PDF", e);
        }
    }

    // Helper Method to Extract Full Subject Name
    private String extractSubjectName(String[] parts) {
        StringBuilder subjectName = new StringBuilder();
        for (int i = 3; i < parts.length - 2; i++) { // Avoid last two columns (Grade, Credits)
            subjectName.append(parts[i]).append(" ");
        }
        return subjectName.toString().trim();
    }
   
    public List<StudentResults> getAllResults() {
        return studentResultsRepository.findAll();
    }

//    //  Fetch Student Results
    public List<StudentResults> getResults(String hallTicket, String batch, String year, String semester) {
        return studentResultsRepository.findByHallTicketAndBatchAndYearAndSemester(hallTicket, batch, year, semester);
    }
    
    
    
    
    
    
    
    
    
//    public List<StudentResults> getResults(String hallTicket, String batch, String year, String semester) {
//        
//        List<StudentResults> results = studentResultsRepository.findByHallTicketAndBatchAndYearAndSemester(hallTicket, batch, year, semester);
//        System.out.println(hallTicket+""+ batch+""+year+""+ semester);
//        System.out.println(results);
//        return results;
//    }

}
