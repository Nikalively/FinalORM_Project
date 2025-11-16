package com.education.finalorm.controller;

import com.education.finalorm.dto.request.LessonRequest;
import com.education.finalorm.dto.response.LessonResponse;
import com.education.finalorm.service.LessonService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/lessons")
@RequiredArgsConstructor
public class LessonController {
    private final LessonService lessonService;

    @PostMapping
    public ResponseEntity<LessonResponse> createLesson(@Valid @RequestBody LessonRequest request) {
        LessonResponse response = lessonService.createLesson(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/module/{moduleId}")
    public ResponseEntity<List<LessonResponse>> getLessonsByModule(@PathVariable UUID moduleId) {
        List<LessonResponse> response = lessonService.getLessonsByModule(moduleId);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LessonResponse> updateLesson(@PathVariable UUID id, @Valid @RequestBody LessonRequest request) {
        LessonResponse response = lessonService.updateLesson(id, request);
        return ResponseEntity.ok(response);
    }
}
