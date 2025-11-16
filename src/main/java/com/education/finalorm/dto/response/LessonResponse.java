package com.education.finalorm.dto.response;

import lombok.Data;
import java.util.UUID;

@Data
public class LessonResponse {
    private UUID id;
    private String title;
    private String content;
    private UUID moduleId;
    private LocalDateTime createdAt;
}
