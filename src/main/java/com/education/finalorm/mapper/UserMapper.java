package com.education.finalorm.mapper;

import org.mapstruct.*;
import com.education.finalorm.dto.request.UserRequest;
import com.education.finalorm.dto.response.UserResponse;
import com.education.finalorm.entity.Student;
import com.education.finalorm.entity.Teacher;
import com.education.finalorm.enums.UserRole;
import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    @Mapping(target = "role", source = "role")
    default User toEntity(UserRequest request, UserRole role) {
        if (role == UserRole.STUDENT) {
            Student student = new Student();
            student.setFirstName(request.getFirstName());
            student.setLastName(request.getLastName());
            student.setEmail(request.getEmail());
            student.setRole(role);
            student.setExternalRef(request.getExternalRef());
            student.setBirthDate(request.getBirthDate());
            student.setStudentNo(request.getStudentNo());
            return student;
        } else if (role == UserRole.TEACHER) {
            Teacher teacher = new Teacher();
            teacher.setFirstName(request.getFirstName());
            teacher.setLastName(request.getLastName());
            teacher.setEmail(request.getEmail());
            teacher.setRole(role);
            teacher.setExternalRef(request.getExternalRef());
            teacher.setBirthDate(request.getBirthDate());
            teacher.setAcademicTitle(request.getAcademicTitle());
            return teacher;
        }
        return null;
    }

    UserResponse toResponse(Object user);

    List<UserResponse> toResponseList(List<?> users);

    @AfterMapping
    default void setType(@MappingTarget UserResponse response, @Source Object user) {
        if (user instanceof Student) {
            response.setType("STUDENT");
        } else if (user instanceof Teacher) {
            response.setType("TEACHER");
        } else {
            response.setType("ADMIN");
        }
    }
}
