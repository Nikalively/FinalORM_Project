package com.education.finalorm.controller;

import com.education.finalorm.dto.request.QuizRequest;
import com.education.finalorm.dto.response.QuizResponse;
import com.education.finalorm.service.QuizService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/quizzes")
@RequiredArgsConstructor
public class QuizController {
    private final QuizService quizService;

    @PostMapping
    public ResponseEntity<QuizResponse> createQuiz(@Valid @RequestBody QuizRequest request) {
        QuizResponse response = quizService.createQuiz(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuizResponse> getQuiz(@PathVariable UUID id) {
        QuizResponse response = quizService.getQuizById(id);
        return ResponseEntity.ok(response);
    }
}
