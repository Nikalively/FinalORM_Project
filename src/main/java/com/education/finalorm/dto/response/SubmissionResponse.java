package com.education.finalorm.dto.response;

import lombok.Data;
import com.education.finalorm.enums.SubmissionStatus;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class SubmissionResponse {
    private UUID id;
    private String content;
    private String fileUrl;
    private LocalDateTime submittedAt;
    private SubmissionStatus status;
    private Double score;
    private String feedback;
    private UUID studentId;
    private UUID assignmentId;
    private LocalDateTime createdAt;
}
