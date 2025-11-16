package com.education.finalorm.controller;

import com.education.finalorm.dto.request.SubmissionRequest;
import com.education.finalorm.dto.response.SubmissionResponse;
import com.education.finalorm.service.SubmissionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/submissions")
@RequiredArgsConstructor
public class SubmissionController {
    private final SubmissionService submissionService;

    @PostMapping
    public ResponseEntity<SubmissionResponse> submit(@Valid @RequestBody SubmissionRequest request) {
        SubmissionResponse response = submissionService.submitAssignment(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<SubmissionResponse>> getSubmissionsByStudent(@PathVariable UUID studentId) {
        List<SubmissionResponse> response = submissionService.getSubmissionsByStudent(studentId);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}/grade")
    public ResponseEntity<SubmissionResponse> grade(@PathVariable UUID id, @RequestParam Double score, @RequestParam String feedback) {
        SubmissionResponse response = submissionService.gradeSubmission(id, score, feedback);
        return ResponseEntity.ok(response);
    }
}
