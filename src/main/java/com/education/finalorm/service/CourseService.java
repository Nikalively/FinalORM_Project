package com.education.finalorm.service;

import com.education.finalorm.dto.request.CourseRequest;
import com.education.finalorm.dto.response.CourseDetailResponse;
import com.education.finalorm.dto.response.CourseResponse;
import com.education.finalorm.entity.Category;
import com.education.finalorm.entity.Course;
import com.education.finalorm.entity.Tag;
import com.education.finalorm.entity.Teacher;
import com.education.finalorm.exception.BusinessException;
import com.education.finalorm.exception.ResourceNotFoundException;
import com.education.finalorm.mapper.CourseMapper;
import com.education.finalorm.repository.CategoryRepository;
import com.education.finalorm.repository.CourseRepository;
import com.education.finalorm.repository.TagRepository;
import com.education.finalorm.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;
    private final CategoryRepository categoryRepository;
    private final TeacherRepository teacherRepository;
    private final TagRepository tagRepository;
    private final CourseMapper courseMapper;

    @Transactional
    public CourseResponse createCourse(CourseRequest request) {
        if (courseRepository.findByTitleIgnoreCase(request.getTitle()).isPresent()) {
            throw new BusinessException("Course with title already exists");
        }
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
        Teacher teacher = teacherRepository.findById(request.getTeacherId())
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found"));
        Set<Tag> tags = request.getTagIds().stream()
                .map(id -> tagRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Tag not found with id: " + id)))
                .collect(Collectors.toSet());
        Course course = courseMapper.toEntity(request);
        course.setCategory(category);
        course.setTeacher(teacher);
        course.setTags(tags);
        course = courseRepository.save(course);
        return courseMapper.toResponse(course);
    }

    @Transactional(readOnly = true)
    public CourseResponse getCourseById(UUID id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + id));
        return courseMapper.toResponse(course);
    }

    @Transactional(readOnly = true)
    public CourseDetailResponse getCourseDetailById(UUID id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + id));
        return courseMapper.toDetailResponse(course);
    }

    @Transactional(readOnly = true)
    public List<CourseResponse> getAllCourses() {
        return courseMapper.toResponseList(courseRepository.findAll());
    }

    @Transactional(readOnly = true)
    public List<CourseResponse> searchCourses(String query) {
        return courseMapper.toResponseList(courseRepository.searchCourses(query));
    }

    @Transactional
    public CourseResponse updateCourse(UUID id, CourseRequest request) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + id));
        courseMapper.updateEntityFromRequest(request, course);

        if (request.getCategoryId() != null) {
            Category category = categoryRepository.findById(request.getCategoryId())
                    .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
            course.setCategory(category);
        }
        if (request.getTeacherId() != null) {
            Teacher teacher = teacherRepository.findById(request.getTeacherId())
                    .orElseThrow(() -> new ResourceNotFoundException("Teacher not found"));
            course.setTeacher(teacher);
        }

        course = courseRepository.save(course);
        return courseMapper.toResponse(course);
    }

    @Transactional
    public void deleteCourse(UUID id) {
        if (!courseRepository.existsById(id)) {
            throw new ResourceNotFoundException("Course not found with id: " + id);
        }
        courseRepository.deleteById(id);
    }
}
