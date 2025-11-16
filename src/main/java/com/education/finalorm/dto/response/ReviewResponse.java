package com.education.finalorm.dto.response;

import lombok.Data;
import java.util.UUID;

@Data
public class ReviewResponse {
    private UUID id;
    private Integer rating;
    private String comment;
    private UUID studentId;
    private UUID courseId;
    private LocalDateTime createdAt;
}
