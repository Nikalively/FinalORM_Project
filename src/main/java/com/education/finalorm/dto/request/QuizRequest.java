package com.education.finalorm.dto.request;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import java.time.Duration;

@Data
public class QuizRequest {
    @NotBlank
    private String title;

    private Duration timeLimit;

    private UUID moduleId;
}
