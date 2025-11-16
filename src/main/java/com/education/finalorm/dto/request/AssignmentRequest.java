package com.education.finalorm.dto.request;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class AssignmentRequest {
    @NotBlank
    private String title;

    private String description;

    private LocalDateTime dueDate;

    private Integer maxScore;

    private UUID lessonId;
}
