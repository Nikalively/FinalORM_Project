package com.education.finalorm.mapper;

import org.mapstruct.*;
import com.education.finalorm.dto.request.AssignmentRequest;
import com.education.finalorm.dto.response.AssignmentResponse;
import com.education.finalorm.entity.Assignment;
import com.education.finalorm.entity.Lesson;
import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AssignmentMapper {
    @Mapping(target = "lesson", source = "lessonId", qualifiedByName = "idToLesson")
    Assignment toEntity(AssignmentRequest request);

    AssignmentResponse toResponse(Assignment assignment);

    List<AssignmentResponse> toResponseList(List<Assignment> assignments);

    @Named("idToLesson")
    default Lesson idToLesson(UUID id) {
        if (id == null) return null;
        Lesson l = new Lesson();
        l.setId(id);
        return l;
    }
}
