package com.education.finalorm.dto.response;

import lombok.Data;
import com.education.finalorm.dto.nested.InstructorBasicInfo;
import com.education.finalorm.dto.nested.ModuleBasicInfo;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
public class CourseDetailResponse {
    private UUID id;
    private String title;
    private String description;
    private InstructorBasicInfo teacher;
    private List<ModuleBasicInfo> modules;
    private Set<String> tags;
    private LocalDateTime createdAt;
}
