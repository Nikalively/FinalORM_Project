package com.education.finalorm.controller;

import com.education.finalorm.dto.request.AssignmentRequest;
import com.education.finalorm.dto.response.AssignmentResponse;
import com.education.finalorm.service.AssignmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/assignments")
@RequiredArgsConstructor
public class AssignmentController {
    private final AssignmentService assignmentService;

    @PostMapping
    public ResponseEntity<AssignmentResponse> createAssignment(@Valid @RequestBody AssignmentRequest request) {
        AssignmentResponse response = assignmentService.createAssignment(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/lesson/{lessonId}")
    public ResponseEntity<List<AssignmentResponse>> getAssignmentsByLesson(@PathVariable UUID lessonId) {
        List<AssignmentResponse> response = assignmentService.getAssignmentsByLesson(lessonId);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AssignmentResponse> updateAssignment(@PathVariable UUID id, @Valid @RequestBody AssignmentRequest request) {
        AssignmentResponse response = assignmentService.updateAssignment(id, request);
        return ResponseEntity.ok(response);
    }
}
