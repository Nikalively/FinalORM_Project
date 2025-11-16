package com.education.finalorm.dto.request;

import lombok.Data;
import java.util.UUID;

@Data
public class SubmissionRequest {
    private String content;

    private String fileUrl;

    private UUID studentId;

    private UUID assignmentId;
}
