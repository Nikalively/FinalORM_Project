package com.education.finalorm.service;

import com.education.finalorm.dto.request.SubmissionRequest;
import com.education.finalorm.dto.response.SubmissionResponse;
import com.education.finalorm.entity.Assignment;
import com.education.finalorm.entity.Enrollment;
import com.education.finalorm.entity.Student;
import com.education.finalorm.entity.Submission;
import com.education.finalorm.enums.EnrollmentStatus;
import com.education.finalorm.enums.SubmissionStatus;
import com.education.finalorm.exception.BusinessException;
import com.education.finalorm.exception.ResourceNotFoundException;
import com.education.finalorm.mapper.SubmissionMapper;
import com.education.finalorm.repository.AssignmentRepository;
import com.education.finalorm.repository.EnrollmentRepository;
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
    private final EnrollmentRepository enrollmentRepository;
    private final SubmissionMapper submissionMapper;

    @Transactional
    public SubmissionResponse submitAssignment(SubmissionRequest request) {
        Student student = studentRepository.findById(request.getStudentId())
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));
        Assignment assignment = assignmentRepository.findById(request.getAssignmentId())
                .orElseThrow(() -> new ResourceNotFoundException("Assignment not found"));
        Enrollment enrollment = enrollmentRepository.findByStudentIdAndCourseId(student.getId(), assignment.getLesson().getModule().getCourse().getId())
                .orElseThrow(() -> new BusinessException("Student not enrolled in the course"));
        if (enrollment.getStatus() != EnrollmentStatus.ACTIVE) {
            throw new BusinessException("Cannot submit: enrollment not active");
        }
        if (submissionRepository.findByStudentIdAndAssignmentId(student.getId(), assignment.getId()).isPresent()) {
            throw new BusinessException("Submission already exists");
        }
        Submission submission = submissionMapper.toEntity(request);
        submission.setStudent(student);
        submission.setAssignment(assignment);
        submission = submissionRepository.save(submission);
        return submissionMapper.toResponse(submission);
    }

    @Transactional(readOnly = true)
    public List<SubmissionResponse> getSubmissionsByStudent(UUID studentId) {
        return submissionMapper.toResponseList(submissionRepository.findByStudentId(studentId));
    }

    @Transactional
    public SubmissionResponse gradeSubmission(UUID id, Double score, String feedback) {
        Submission submission = submissionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Submission not found with id: " + id));
        submission.setScore(score);
        submission.setFeedback(feedback);
        submission.setStatus(SubmissionStatus.GRADED);
        submission = submissionRepository.save(submission);
        return submissionMapper.toResponse(submission);
    }
}
