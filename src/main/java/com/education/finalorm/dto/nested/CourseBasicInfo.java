package com.education.finalorm.dto.nested;

import lombok.Data;
import java.util.UUID;

@Data
public class CourseBasicInfo {
    private UUID id;
    private String title;
}
