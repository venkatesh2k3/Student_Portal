package com.studentportal.chatBot;

import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/chatbot")
public class ChatbotController {
    private final GeminiService geminiService;

    public ChatbotController(GeminiService geminiService) {
        this.geminiService = geminiService;
    }

    @PostMapping("/ask")
    public String chat(@RequestParam String message, 
                       @RequestParam String hallTicket,
                       HttpServletRequest request) {

        // Extract JWT Token from Request Header
        String jwtToken = request.getHeader(HttpHeaders.AUTHORIZATION);
        
        return geminiService.askChatbot(message, hallTicket, jwtToken);
    }
}
