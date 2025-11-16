package com.education.finalorm.dto.response;

import lombok.Data;
import com.education.finalorm.enums.EnrollmentStatus;
import com.education.finalorm.dto.nested.StudentBasicInfo;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class EnrollmentResponse {
    private UUID id;
    private EnrollmentStatus status;
    private LocalDateTime enrollDate;
    private StudentBasicInfo student;
    private UUID courseId;
    private LocalDateTime createdAt;
}
