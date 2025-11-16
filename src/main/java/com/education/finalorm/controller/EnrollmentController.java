package com.education.finalorm.controller;

import com.education.finalorm.dto.request.EnrollmentRequest;
import com.education.finalorm.dto.response.EnrollmentResponse;
import com.education.finalorm.enums.EnrollmentStatus;
import com.education.finalorm.service.EnrollmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/enrollments")
@RequiredArgsConstructor
public class EnrollmentController {
    private final EnrollmentService enrollmentService;

    @PostMapping
    public ResponseEntity<EnrollmentResponse> enroll(@Valid @RequestBody EnrollmentRequest request) {
        EnrollmentResponse response = enrollmentService.enrollStudent(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<EnrollmentResponse>> getEnrollmentsByStudent(@PathVariable UUID studentId) {
        List<EnrollmentResponse> response = enrollmentService.getEnrollmentsByStudent(studentId);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<EnrollmentResponse> updateStatus(@PathVariable UUID id, @RequestParam EnrollmentStatus status) {
        EnrollmentResponse response = enrollmentService.updateEnrollmentStatus(id, status);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> dropEnrollment(@PathVariable UUID id) {
        enrollmentService.dropEnrollment(id);
        return ResponseEntity.noContent().build();
    }
}
