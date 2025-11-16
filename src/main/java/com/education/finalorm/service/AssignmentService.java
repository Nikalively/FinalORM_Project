package com.education.finalorm.service;

import com.education.finalorm.dto.request.AssignmentRequest;
import com.education.finalorm.dto.response.AssignmentResponse;
import com.education.finalorm.entity.Assignment;
import com.education.finalorm.entity.Lesson;
import com.education.finalorm.exception.ResourceNotFoundException;
import com.education.finalorm.mapper.AssignmentMapper;
import com.education.finalorm.repository.AssignmentRepository;
import com.education.finalorm.repository.LessonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AssignmentService {
    private final AssignmentRepository assignmentRepository;
    private final LessonRepository lessonRepository;
    private final AssignmentMapper assignmentMapper;

    @Transactional
    public AssignmentResponse createAssignment(AssignmentRequest request) {
        Lesson lesson = lessonRepository.findById(request.getLessonId())
                .orElseThrow(() -> new ResourceNotFoundException("Lesson not found"));
        Assignment assignment = assignmentMapper.toEntity(request);
        assignment.setLesson(lesson);
        assignment = assignmentRepository.save(assignment);
        return assignmentMapper.toResponse(assignment);
    }

    @Transactional(readOnly = true)
    public List<AssignmentResponse> getAssignmentsByLesson(UUID lessonId) {
        return assignmentMapper.toResponseList(assignmentRepository.findByLessonId(lessonId));
    }

    @Transactional
    public AssignmentResponse updateAssignment(UUID id, AssignmentRequest request) {
        Assignment assignment = assignmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Assignment not found with id: " + id));
        assignment.setTitle(request.getTitle());
        assignment.setDescription(request.getDescription());
        assignment.setDueDate(request.getDueDate());
        assignment.setMaxScore(request.getMaxScore());
        assignment = assignmentRepository.save(assignment);
        return assignmentMapper.toResponse(assignment);
    }
}
