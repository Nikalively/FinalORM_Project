package com.education.finalorm.dto.request;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import java.util.Set;
import java.util.UUID;

@Data
public class CourseRequest {
    @NotBlank
    private String title;

    private String description;

    private UUID categoryId;

    private UUID teacherId;

    private Set<UUID> tagIds;
}
