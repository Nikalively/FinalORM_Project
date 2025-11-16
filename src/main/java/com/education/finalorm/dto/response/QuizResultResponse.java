package com.education.finalorm.dto.response;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class QuizResultResponse {
    private UUID id;
    private Double score;
    private LocalDateTime takenAt;
    private UUID studentId;
    private UUID quizId;
    private LocalDateTime createdAt;
}
