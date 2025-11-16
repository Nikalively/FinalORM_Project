package com.education/finalorm.dto.nested;

import lombok.Data;
import java.util.UUID;

@Data
public class ModuleBasicInfo {
    private UUID id;
    private String title;
    private Integer orderIndex;
}
