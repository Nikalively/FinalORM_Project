package com.education.finalorm.dto.request;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import java.util.UUID;

@Data
public class LessonRequest {
    @NotBlank
    private String title;

    private String content;

    private UUID moduleId;
}
