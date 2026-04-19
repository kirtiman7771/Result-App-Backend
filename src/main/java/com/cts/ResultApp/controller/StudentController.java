package com.cts.ResultApp.controller;

import com.cts.ResultApp.dto.ResultResponse;
import com.cts.ResultApp.service.ResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controller for students to view their own academic results.
 */
@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class StudentController {

    private final ResultService resultService;

    /**
     * Fetches results for the currently authenticated student.
     * No try-catch required as exceptions are handled globally.
     */
    @GetMapping("/results")
    public ResponseEntity<List<ResultResponse>> getMyResults() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        // This method call now matches the updated Service signature
        List<ResultResponse> results = resultService.getResultsByStudentUsername(username);
        return ResponseEntity.ok(results);
    }
}
