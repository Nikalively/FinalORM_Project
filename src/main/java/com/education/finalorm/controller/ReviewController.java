package com.education.finalorm.controller;

import com.education.finalorm.dto.request.ReviewRequest;
import com.education.finalorm.dto.response.ReviewResponse;
import com.education.finalorm.service.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping
    public ResponseEntity<ReviewResponse> createReview(@Valid @RequestBody ReviewRequest request) {
        ReviewResponse response = reviewService.createReview(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<ReviewResponse>> getReviewsByCourse(@PathVariable UUID courseId) {
        List<ReviewResponse> response = reviewService.getReviewsByCourse(courseId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/course/{courseId}/average-rating")
    public ResponseEntity<Double> getAverageRating(@PathVariable UUID courseId) {
        Double rating = reviewService.getAverageRating(courseId);
        return ResponseEntity.ok(rating);
    }
}
