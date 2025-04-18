package com.studentportal.library;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.studentportal.model.User;
import com.studentportal.repo.UserRepository;
import com.studentportal.util.JwtUtil;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/library")
public class LibraryController {

    @Autowired
    private LibraryService libraryService;
    
    @Autowired
	private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/rate")
    public ResponseEntity<String> rateBook(@RequestParam Long bookId,
                                           @RequestParam int rating,
                                           @RequestHeader("Authorization") String token) {
        String jwtToken = token.startsWith("Bearer ") ? token.substring(7) : token;
        String hallTicket = jwtUtil.extractUsername(jwtToken); // Extract hallTicket from token
        libraryService.rateBook(bookId, hallTicket, rating);
        return ResponseEntity.ok("Rating submitted successfully!");
    }
    @PostMapping("/add")
    public ResponseEntity<String> addBook(@RequestBody Book book, @RequestHeader("Authorization") String token) {
        String jwtToken = token.startsWith("Bearer ") ? token.substring(7) : token;
        String username = jwtUtil.extractUsername(jwtToken);

        Optional<User> userOpt = userRepository.findByUsername(username);

        if (userOpt.isPresent() && userOpt.get().getRole().equalsIgnoreCase("ADMIN")) {
            libraryService.addBook(book);
            return ResponseEntity.ok("Book added successfully!");
        }

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Unauthorized: Admin role required");
    }


    @GetMapping("/books")
    public ResponseEntity<List<Book>> getBooks() {
        List<Book> books = libraryService.getBooksSortedByRating();
        return ResponseEntity.ok(books);
    }
}
