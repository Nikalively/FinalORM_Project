package com.education.finalorm.dto.response;

import lombok.Data;
import com.education.finalorm.enums.UserRole;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class UserResponse {
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private UserRole role;
    private String externalRef;
    private LocalDate birthDate;
    private String type;
    private LocalDateTime createdAt;
}
