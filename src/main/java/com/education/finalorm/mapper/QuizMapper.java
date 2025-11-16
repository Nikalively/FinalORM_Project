package com.education.finalorm.mapper;

import org.mapstruct.*;
import com.education.finalorm.dto.request.QuizRequest;
import com.education.finalorm.dto.response.QuizResponse;
import com.education.finalorm.entity.Quiz;
import com.education.finalorm.entity.Module;
import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface QuizMapper {
    @Mapping(target = "module", source = "moduleId", qualifiedByName = "idToModule")
    Quiz toEntity(QuizRequest request);

    QuizResponse toResponse(Quiz quiz);

    List<QuizResponse> toResponseList(List<Quiz> quizzes);

    @Named("idToModule")
    default Module idToModule(UUID id) {
        if (id == null) return null;
        Module m = new Module();
        m.setId(id);
        return m;
    }
}
