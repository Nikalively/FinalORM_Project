package com.education.finalorm.dto.response;

import lombok.Data;
import com.education.finalorm.dto.nested.CategoryBasicInfo;
import com.education.finalorm.dto.nested.InstructorBasicInfo;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class CourseResponse {
    private UUID id;
    private String title;
    private String description;
    private CategoryBasicInfo category;
    private InstructorBasicInfo teacher;
    private LocalDateTime createdAt;
}
