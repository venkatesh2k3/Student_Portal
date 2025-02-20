package com.studentportal.results;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.studentportal.model.User;
import com.studentportal.repo.UserRepository;
import com.studentportal.util.JwtUtil;
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*", allowCredentials = "true")
@RestController
@RequestMapping("/api/results")
public class ResultsController {
	private final StudentResultsRepository studentResultsRepository;
    private final ResultsService resultsService;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
   
    
    public ResultsController(ResultsService resultsService, JwtUtil jwtUtil, UserRepository userRepository, StudentResultsRepository studentResultsRepository) {
        this.resultsService = resultsService;
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
        this.studentResultsRepository=studentResultsRepository;
    }

    //  Admin Upload Results PDF
    @PostMapping("/upload")
    public ResponseEntity<String> uploadResults(@RequestParam("file") MultipartFile file,
                                                @RequestParam("batch") String batch,
                                                @RequestParam("year") String year,
                                                @RequestParam("semester") String semester,
                                                @RequestHeader("Authorization") String token) {
        String jwtToken = token.startsWith("Bearer ") ? token.substring(7) : token;
        String username = jwtUtil.extractUsername(jwtToken);

        Optional<User> userOpt = userRepository.findByUsername(username);

        if (userOpt.isPresent() && userOpt.get().getRole().equalsIgnoreCase("ADMIN")) {
            resultsService.processResultsPDF(file, batch, year, semester);
            return ResponseEntity.ok("Results uploaded successfully!");
        }

        throw new RuntimeException("Unauthorized: Admin role required");
    }

    @GetMapping("/all")
    public ResponseEntity<List<StudentResults>> getAllResults() {
        List<StudentResults> results = resultsService.getAllResults();
        return ResponseEntity.ok(results);
    }
    
    @GetMapping("/hallticket/{hallTicket}")
    public List<StudentResults> getResultsByHallTicket(@PathVariable String hallTicket) {
        return studentResultsRepository.findByHallTicket(hallTicket);
    }
    
   
    
    
    
    
    @GetMapping("hallticket/{batch}/{year}/{semester}")
    public List<StudentResults> getResults(@RequestHeader("Authorization") String token, 
                                           @PathVariable String batch, 
                                           @PathVariable String year, 
                                           @PathVariable String semester) {
        // Extract username (hallTicket) from JWT
        String jwtToken = token.startsWith("Bearer ") ? token.substring(7) : token;
        String hallTicket = jwtUtil.extractUsername(jwtToken);

        System.out.println("🔍 Extracted HallTicket: " + hallTicket);

        // Fetch results from DB
        List<StudentResults> results = resultsService.getResults(hallTicket, batch, year, semester);

        System.out.println("✅ Results found: " + results.size());
        return results;
    }

}
