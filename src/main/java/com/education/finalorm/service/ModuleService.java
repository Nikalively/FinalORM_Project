package com.education.finalorm.service;

import com.education.finalorm.dto.request.ModuleRequest;
import com.education.finalorm.dto.response.ModuleResponse;
import com.education.finalorm.entity.Course;
import com.education.finalorm.entity.Module;
import com.education.finalorm.exception.ResourceNotFoundException;
import com.education.finalorm.mapper.ModuleMapper;
import com.education.finalorm.repository.CourseRepository;
import com.education.finalorm.repository.ModuleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ModuleService {
    private final ModuleRepository moduleRepository;
    private final CourseRepository courseRepository;
    private final ModuleMapper moduleMapper;

    @Transactional
    public ModuleResponse createModule(ModuleRequest request) {
        Course course = courseRepository.findById(request.getCourseId())
                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));
        Module module = moduleMapper.toEntity(request);
        module.setCourse(course);
        module = moduleRepository.save(module);
        return moduleMapper.toResponse(module);
    }

    @Transactional(readOnly = true)
    public List<ModuleResponse> getModulesByCourse(UUID courseId) {
        return moduleMapper.toResponseList(moduleRepository.findByCourseIdOrderByOrderIndexAsc(courseId));
    }

    @Transactional
    public ModuleResponse updateModule(UUID id, ModuleRequest request) {
        Module module = moduleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Module not found with id: " + id));
        module.setTitle(request.getTitle());
        module.setOrderIndex(request.getOrderIndex());
        if (request.getCourseId() != null) {
            Course course = courseRepository.findById(request.getCourseId())
                    .orElseThrow(() -> new ResourceNotFoundException("Course not found"));
            module.setCourse(course);
        }
        module = moduleRepository.save(module);
        return moduleMapper.toResponse(module);
    }
}
