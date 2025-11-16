package com.education.finalorm.mapper;

import org.mapstruct.*;
import com.education.finalorm.dto.request.LessonRequest;
import com.education.finalorm.dto.response.LessonResponse;
import com.education.finalorm.entity.Lesson;
import com.education.finalorm.entity.Module;
import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LessonMapper {
    @Mapping(target = "module", source = "moduleId", qualifiedByName = "idToModule")
    Lesson toEntity(LessonRequest request);

    LessonResponse toResponse(Lesson lesson);

    List<LessonResponse> toResponseList(List<Lesson> lessons);

    @Named("idToModule")
    default Module idToModule(UUID id) {
        if (id == null) return null;
        Module m = new Module();
        m.setId(id);
        return m;
    }
}
