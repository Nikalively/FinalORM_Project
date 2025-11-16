package com.education.finalorm.dto.response;

import lombok.Data;
import java.util.UUID;

@Data
public class ProfileResponse {
    private UUID id;
    private String bio;
    private String avatarUrl;
    private UUID userId;
    private LocalDateTime createdAt;
}
