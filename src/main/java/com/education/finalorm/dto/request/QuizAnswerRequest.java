package com.education.finalorm.dto.request;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;

@Data
public class QuizAnswerRequest {
    @NotBlank
    private String text;

    private Boolean isCorrect;
}
