package com.education.finalorm.dto.nested;

import lombok.Data;
import java.util.UUID;

@Data
public class LessonBasicInfo {
    private UUID id;
    private String title;
}
