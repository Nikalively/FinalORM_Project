package com.education.finalorm.dto.response;

import lombok.Data;
import java.time.Duration;
import java.util.UUID;

@Data
public class QuizResponse {
    private UUID id;
    private String title;
    private Duration timeLimit;
    private UUID moduleId;
}
