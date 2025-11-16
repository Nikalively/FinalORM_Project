package com.education.finalorm.dto.response;

import lombok.Data;
import java.util.UUID;

@Data
public class ModuleResponse {
    private UUID id;
    private String title;
    private Integer orderIndex;
    private UUID courseId;
    private LocalDateTime createdAt;
}
