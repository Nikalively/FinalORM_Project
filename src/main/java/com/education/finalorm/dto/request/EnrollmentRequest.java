package com.education.finalorm.dto.request;

import lombok.Data;
import com.education.finalorm.enums.EnrollmentStatus;
import java.util.UUID;

@Data
public class EnrollmentRequest {
    private EnrollmentStatus status;

    private UUID studentId;

    private UUID courseId;
}
