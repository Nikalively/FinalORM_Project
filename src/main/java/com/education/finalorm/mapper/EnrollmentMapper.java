package com.education.finalorm.mapper;

import org.mapstruct.*;
import com.education.finalorm.dto.nested.StudentBasicInfo;
import com.education.finalorm.dto.request.EnrollmentRequest;
import com.education.finalorm.dto.response.EnrollmentResponse;
import com.education.finalorm.entity.Enrollment;
import com.education.finalorm.entity.Student;
import com.education.finalorm.entity.Course;
import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EnrollmentMapper {
    @Mapping(target = "student", source = "studentId", qualifiedByName = "idToStudent")
    @Mapping(target = "course", source = "courseId", qualifiedByName = "idToCourse")
    Enrollment toEntity(EnrollmentRequest request);

    @Mapping(target = "student", source = "student", qualifiedByName = "studentToBasicInfo")
    EnrollmentResponse toResponse(Enrollment enrollment);

    List<EnrollmentResponse> toResponseList(List<Enrollment> enrollments);

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

    @Named("studentToBasicInfo")
    default StudentBasicInfo studentToBasicInfo(Student student) {
        if (student == null) return null;
        StudentBasicInfo info = new StudentBasicInfo();
        info.setId(student.getId());
        info.setFirstName(student.getFirstName());
        info.setLastName(student.getLastName());
        info.setStudentNo(student.getStudentNo());
        return info;
    }
}
