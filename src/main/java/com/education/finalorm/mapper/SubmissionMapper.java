package com.education.finalorm.mapper;

import org.mapstruct.*;
import com.education.finalorm.dto.request.SubmissionRequest;
import com.education.finalorm.dto.response.SubmissionResponse;
import com.education.finalorm.entity.Submission;
import com.education.finalorm.entity.Student;
import com.education.finalorm.entity.Assignment;
import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SubmissionMapper {
    @Mapping(target = "student", source = "studentId", qualifiedByName = "idToStudent")
    @Mapping(target = "assignment", source = "assignmentId", qualifiedByName = "idToAssignment")
    @Mapping(target = "submittedAt", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "status", constant = "PENDING")
    Submission toEntity(SubmissionRequest request);

    SubmissionResponse toResponse(Submission submission);

    List<SubmissionResponse> toResponseList(List<Submission> submissions);

    @Named("idToStudent")
    default Student idToStudent(UUID id) {
        if (id == null) return null;
        Student s = new Student();
        s.setId(id);
        return s;
    }

    @Named("idToAssignment")
    default Assignment idToAssignment(UUID id) {
        if (id == null) return null;
        Assignment a = new Assignment();
        a.setId(id);
        return a;
    }
}
