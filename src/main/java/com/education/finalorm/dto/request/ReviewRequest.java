package com.education.finalorm.dto.request;

import lombok.Data;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class ReviewRequest {
    @NotNull
    private Integer rating;

    private String comment;

    private UUID studentId;

    private UUID courseId;
}
