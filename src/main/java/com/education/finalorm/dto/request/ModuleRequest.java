package com.education.finalorm.dto.request;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import java.util.UUID;

@Data
public class ModuleRequest {
    @NotBlank
    private String title;

    private Integer orderIndex;

    private UUID courseId;
}
