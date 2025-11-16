package com.education.finalorm.mapper;

import org.mapstruct.*;
import com.education.finalorm.dto.request.ModuleRequest;
import com.education.finalorm.dto.response.ModuleResponse;
import com.education.finalorm.entity.Module;
import com.education.finalorm.entity.Course;
import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ModuleMapper {
    @Mapping(target = "course", source = "courseId", qualifiedByName = "idToCourse")
    Module toEntity(ModuleRequest request);

    ModuleResponse toResponse(Module module);

    List<ModuleResponse> toResponseList(List<Module> modules);

    @Named("idToCourse")
    default Course idToCourse(UUID id) {
        if (id == null) return null;
        Course c = new Course();
        c.setId(id);
        return c;
    }
}
