package com.education.finalorm.mapper;

import com.education.finalorm.dto.request.UserRequest;
import com.education.finalorm.dto.response.UserResponse;
import com.education.finalorm.entity.Student;
import com.education.finalorm.entity.Teacher;
import com.education.finalorm.entity.User;
import com.education.finalorm.enums.UserRole;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "profile", ignore = true)
    @Mapping(target = "courses", ignore = true)
    @Mapping(target = "enrollments", ignore = true)
    @Mapping(target = "submissions", ignore = true)
    @Mapping(target = "reviews", ignore = true)
    @Mapping(target = "studentNo", source = "studentNo")
    @Mapping(target = "teacherNo", source = "teacherNo")
    Student toStudent(UserRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "profile", ignore = true)
    @Mapping(target = "courses", ignore = true)
    @Mapping(target = "enrollments", ignore = true)
    @Mapping(target = "submissions", ignore = true)
    @Mapping(target = "reviews", ignore = true)
    @Mapping(target = "studentNo", source = "studentNo")
    @Mapping(target = "teacherNo", source = "teacherNo")
    Teacher toTeacher(UserRequest request);

    @Mapping(target = "role", expression = "java(getRole(user))")
    UserResponse toResponse(User user);

    @Named("getRole")
    default UserRole getRole(User user) {
        if (user instanceof Student) {
            return UserRole.STUDENT;
        } else if (user instanceof Teacher) {
            return UserRole.TEACHER;
        }
        return UserRole.ADMIN;
    }
}
