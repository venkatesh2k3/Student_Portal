package com.studentportal.chatBot;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class GeminiService {
    private static final Logger logger = LoggerFactory.getLogger(GeminiService.class);
    private static final String GEMINI_URL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:generateContent";
    private static final String API_KEY = "AIzaSyBXWl4sPOalEsS5E7SdRRt1CLPRcbaID68";

    public String askChatbot(String userQuery, String hallTicket, String jwtToken) {
        logger.info("Received user query: {}", userQuery);
        String aiResponse = callGemini(userQuery);

        String apiUrl = "";
        if (aiResponse.toLowerCase().contains("gpa") || aiResponse.toLowerCase().contains("cgpa")) {
            apiUrl = "http://localhost:8080/api/results/gpa/" + hallTicket;
        } else if (aiResponse.toLowerCase().contains("result")) {
            apiUrl = "http://localhost:8080/api/results/hallticket/" + hallTicket;
        } else if (aiResponse.toLowerCase().contains("student details")) {
            apiUrl = "http://localhost:8080/api/student/details/";
        }

        if (!apiUrl.isEmpty()) {
            logger.info("Fetching student data from API: {}", apiUrl);
            return fetchStudentData(apiUrl, jwtToken);
        }

        return aiResponse;
    }

    private String callGemini(String userQuery) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("contents", List.of(
                Map.of("role", "user", "parts", List.of(Map.of("text", userQuery)))
        ));

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);
        String requestUrl = GEMINI_URL + "?key=" + API_KEY;

        logger.info("Sending request to Gemini API: URL = {}", requestUrl);

        try {
            ResponseEntity<Map> response = restTemplate.postForEntity(requestUrl, entity, Map.class);

            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                Object candidatesObj = response.getBody().get("candidates");
                if (candidatesObj instanceof List) {
                    List<Map<String, Object>> candidates = (List<Map<String, Object>>) candidatesObj;
                    if (!candidates.isEmpty() && candidates.get(0).containsKey("content")) {
                        Object contentObj = candidates.get(0).get("content");
                        if (contentObj instanceof Map) {
                            Map<String, Object> content = (Map<String, Object>) contentObj;
                            Object partsObj = content.get("parts");
                            if (partsObj instanceof List) {
                                List<Map<String, Object>> parts = (List<Map<String, Object>>) partsObj;
                                if (!parts.isEmpty()) {
                                    return (String) parts.get(0).get("text");
                                }
                            }
                        }
                    }
                }
            }
        } catch (HttpClientErrorException.TooManyRequests e) {
            logger.warn("429 Too Many Requests. Retrying later.");
            return "Too many requests. Please wait and try again.";
        } catch (RestClientException e) {
            logger.error("API request failed: {}", e.getMessage());
            return "Error connecting to Gemini API. Try again later.";
        }
        return "Sorry, I couldn't process your request due to API limitations.";
    }


    private String fetchStudentData(String apiUrl, String jwtToken) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + jwtToken);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        logger.info("Fetching student data from: {}", apiUrl);

        try {
            ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.GET, entity, String.class);
            logger.info("Student API Response: Status = {}, Body = {}", response.getStatusCode(), response.getBody());
            return response.getBody() != null ? response.getBody() : "No data found.";
        } catch (HttpClientErrorException e) {
            logger.error("HTTP Error fetching student data: Status = {}, Response = {}", e.getStatusCode(), e.getResponseBodyAsString());
            return "Error fetching student data: " + e.getStatusCode();
        } catch (Exception e) {
            logger.error("Unexpected error fetching student data: ", e);
            return "Unexpected error fetching student data.";
        }
    }
}