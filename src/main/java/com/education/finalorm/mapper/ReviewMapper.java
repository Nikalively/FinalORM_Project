package com.education.finalorm.mapper;

import org.mapstruct.*;
import com.education.finalorm.dto.request.ReviewRequest;
import com.education.finalorm.dto.response.ReviewResponse;
import com.education.finalorm.entity.CourseReview;
import com.education.finalorm.entity.Student;
import com.education.finalorm.entity.Course;
import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ReviewMapper {
    @Mapping(target = "student", source = "studentId", qualifiedByName = "idToStudent")
    @Mapping(target = "course", source = "courseId", qualifiedByName = "idToCourse")
    CourseReview toEntity(ReviewRequest request);

    ReviewResponse toResponse(CourseReview review);

    List<ReviewResponse> toResponseList(List<CourseReview> reviews);

    @Named("idToStudent")
    default Student idToStudent(UUID id) {
        if (id == null) return null;
        Student s = new Student();
        s.setId(id);
        return s;
    }

    @Named("idToCourse")
    default Course idToCourse(UUID id) {
        if (id == null) return null;
        Course c = new Course();
        c.setId(id);
        return c;
    }
}
