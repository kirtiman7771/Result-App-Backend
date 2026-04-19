package com.cts.ResultApp.controller;

import com.cts.ResultApp.dto.ResultCreationRequest;
import com.cts.ResultApp.dto.ResultResponse;
import com.cts.ResultApp.service.ResultService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST Controller for Teacher actions.
 * Provides endpoints for managing student academic results.
 * Secured via Basic Authentication (Requires ROLE_TEACHER).
 */
@RestController
@RequestMapping("/api/teachers")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class TeacherController {

    private final ResultService resultService;

    /**
     * Creates a new result or updates an existing one if the student and course
     * combination already exists in the database.
     * * @param request DTO containing studentId, courseId, and marks.
     * @return The created or updated ResultResponse.
     */
    @PostMapping("/results")
    public ResponseEntity<ResultResponse> addOrUpdateResult(@Valid @RequestBody ResultCreationRequest request) {
        // This now handles both creation and updates
        ResultResponse response = resultService.createOrUpdateResult(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
