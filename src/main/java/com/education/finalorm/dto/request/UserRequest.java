package com.education.finalorm.dto.request;

import lombok.Data;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import com.education.finalorm.enums.UserRole;
import java.time.LocalDate;

@Data
public class UserRequest {
    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @Email
    @NotBlank
    private String email;

    private UserRole role;

    private String externalRef;

    private LocalDate birthDate;

    private String studentNo;

    private String academicTitle;
}
