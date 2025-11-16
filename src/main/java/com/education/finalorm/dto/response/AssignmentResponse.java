package com.education.finalorm.dto.response;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class AssignmentResponse {
    private UUID id;
    private String title;
    private String description;
    private LocalDateTime dueDate;
    private Integer maxScore;
    private UUID lessonId;
    private LocalDateTime createdAt;
}
