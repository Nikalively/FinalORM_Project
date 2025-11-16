package com.education.finalorm.mapper;

import org.mapstruct.*;
import com.education.finalorm.dto.nested.CategoryBasicInfo;
import com.education.finalorm.dto.nested.InstructorBasicInfo;
import com.education.finalorm.dto.nested.ModuleBasicInfo;
import com.education.finalorm.dto.request.CourseRequest;
import com.education.finalorm.dto.response.CourseResponse;
import com.education.finalorm.dto.response.CourseDetailResponse;
import com.education.finalorm.entity.Course;
import com.education.finalorm.entity.Category;
import com.education.finalorm.entity.Teacher;
import com.education.finalorm.entity.Tag;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CourseMapper {
    @Mapping(target = "category", source = "categoryId", qualifiedByName = "idToCategory")
    @Mapping(target = "teacher", source = "teacherId", qualifiedByName = "idToTeacher")
    Course toEntity(CourseRequest request);

    @Mapping(target = "category", source = "category", qualifiedByName = "categoryToBasicInfo")
    @Mapping(target = "teacher", source = "teacher", qualifiedByName = "teacherToBasicInfo")
    CourseResponse toResponse(Course course);

    @Mapping(target = "teacher", source = "teacher", qualifiedByName = "teacherToBasicInfo")
    @Mapping(target = "modules", source = "modules", qualifiedByName = "modulesToBasicInfo")
    @Mapping(target = "tags", source = "tags", qualifiedByName = "tagsToStrings")
    CourseDetailResponse toDetailResponse(Course course);

    List<CourseResponse> toResponseList(List<Course> courses);

    void updateEntityFromRequest(CourseRequest request, @MappingTarget Course course);

    @Named("teacherToBasicInfo")
    default InstructorBasicInfo teacherToBasicInfo(Teacher teacher) {
        if (teacher == null) return null;
        InstructorBasicInfo info = new InstructorBasicInfo();
        info.setId(teacher.getId());
        info.setFirstName(teacher.getFirstName());
        info.setLastName(teacher.getLastName());
        info.setAcademicTitle(teacher.getAcademicTitle());
        return info;
    }

    @Named("categoryToBasicInfo")
    default CategoryBasicInfo categoryToBasicInfo(Category category) {
        if (category == null) return null;
        CategoryBasicInfo info = new CategoryBasicInfo();
        info.setId(category.getId());
        info.setName(category.getName());
        return info;
    }

    @Named("idToTeacher")
    default Teacher idToTeacher(UUID id) {
        if (id == null) return null;
        Teacher t = new Teacher();
        t.setId(id);
        return t;
    }

    @Named("idToCategory")
    default Category idToCategory(UUID id) {
        if (id == null) return null;
        Category c = new Category();
        c.setId(id);
        return c;
    }

    @Named("modulesToBasicInfo")
    default List<ModuleBasicInfo> modulesToBasicInfo(List<com.education.finalorm.entity.Module> modules) {
        return modules.stream().map(m -> {
            ModuleBasicInfo info = new ModuleBasicInfo();
            info.setId(m.getId());
            info.setTitle(m.getTitle());
            info.setOrderIndex(m.getOrderIndex());
            return info;
        }).toList();
    }

    @Named("tagsToStrings")
    default Set<String> tagsToStrings(Set<Tag> tags) {
        return tags.stream().map(Tag::getName).collect(Collectors.toSet());
    }
}
