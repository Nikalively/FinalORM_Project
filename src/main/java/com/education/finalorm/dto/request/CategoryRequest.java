package com.education.finalorm.dto.request;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;

@Data
public class CategoryRequest {
    @NotBlank
    private String name;
}
