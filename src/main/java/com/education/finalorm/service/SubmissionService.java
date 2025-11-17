package com.education.finalorm.service;

import com.education.finalorm.dto.request.SubmissionRequest;
import com.education.finalorm.dto.response.SubmissionResponse;
import com.education.finalorm.entity.Assignment;
import com.education.finalorm.entity.Student;
import com.education.finalorm.entity.Submission;
import com.education.finalorm.enums.SubmissionStatus;
import com.education.finalorm.exception.ResourceNotFoundException;
import com.education.finalorm.mapper.SubmissionMapper;
import com.education.finalorm.repository.AssignmentRepository;
import com.education.finalorm.repository.StudentRepository;
import com.education.finalorm.repository.SubmissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SubmissionService {

    private final SubmissionRepository submissionRepository;
    private final StudentRepository studentRepository;
    private final AssignmentRepository assignmentRepository;
    private final SubmissionMapper submissionMapper;

    @Transactional
    public SubmissionResponse createSubmission(SubmissionRequest request) {
        Student student = studentRepository.findById(request.getStudentId())
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));
        Assignment assignment = assignmentRepository.findById(request.getAssignmentId())
                .orElseThrow(() -> new ResourceNotFoundException("Assignment not found"));

        Submission submission = submissionMapper.toEntity(request);
        submission.setStudent(student);
        submission.setAssignment(assignment);
        submission.setStatus(SubmissionStatus.PENDING);

        Submission saved = submissionRepository.save(submission);
        return submissionMapper.toResponse(saved);
    }

    @Transactional(readOnly = true)
    public List<SubmissionResponse> getSubmissionsByStudent(UUID studentId) {
        return submissionRepository.findByStudentIdWithDetails(studentId)
                .stream()
                .map(submissionMapper::toResponse)
                .toList();
    }

    @Transactional
    public SubmissionResponse gradeSubmission(UUID submissionId, Integer score, String feedback) {
        Submission submission = submissionRepository.findById(submissionId)
                .orElseThrow(() -> new ResourceNotFoundException("Submission not found"));
        submission.setScore(score);
        submission.setFeedback(feedback);
        submission.setStatus(SubmissionStatus.GRADED);

        Submission saved = submissionRepository.save(submission);
        return submissionMapper.toResponse(saved);
    }
}
