package com.education.finalorm.dto.nested;

import lombok.Data;
import java.util.UUID;

@Data
public class InstructorBasicInfo {
    private UUID id;
    private String firstName;
    private String lastName;
    private String academicTitle;
}
