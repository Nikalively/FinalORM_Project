package com.education.finalorm.service;

import com.education.finalorm.dto.request.LessonRequest;
import com.education.finalorm.dto.response.LessonResponse;
import com.education.finalorm.entity.Lesson;
import com.education.finalorm.entity.Module;
import com.education.finalorm.exception.ResourceNotFoundException;
import com.education.finalorm.mapper.LessonMapper;
import com.education.finalorm.repository.LessonRepository;
import com.education.finalorm.repository.ModuleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LessonService {
    private final LessonRepository lessonRepository;
    private final ModuleRepository moduleRepository;
    private final LessonMapper lessonMapper;

    @Transactional
    public LessonResponse createLesson(LessonRequest request) {
        Module module = moduleRepository.findById(request.getModuleId())
                .orElseThrow(() -> new ResourceNotFoundException("Module not found"));
        Lesson lesson = lessonMapper.toEntity(request);
        lesson.setModule(module);
        lesson = lessonRepository.save(lesson);
        return lessonMapper.toResponse(lesson);
    }

    @Transactional(readOnly = true)
    public List<LessonResponse> getLessonsByModule(UUID moduleId) {
        return lessonMapper.toResponseList(lessonRepository.findByModuleId(moduleId));
    }

    @Transactional
    public LessonResponse updateLesson(UUID id, LessonRequest request) {
        Lesson lesson = lessonRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Lesson not found with id: " + id));
        lesson.setTitle(request.getTitle());
        lesson.setContent(request.getContent());
        if (request.getModuleId() != null) {
            Module module = moduleRepository.findById(request.getModuleId())
                    .orElseThrow(() -> new ResourceNotFoundException("Module not found"));
            lesson.setModule(module);
        }
        lesson = lessonRepository.save(lesson);
        return lessonMapper.toResponse(lesson);
    }
}
