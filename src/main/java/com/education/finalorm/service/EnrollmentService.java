package com.education.finalorm.service;

import com.education.finalorm.dto.request.EnrollmentRequest;
import com.education.finalorm.dto.response.EnrollmentResponse;
import com.education.finalorm.entity.Course;
import com.education.finalorm.entity.Enrollment;
import com.education.finalorm.entity.Student;
import com.education.finalorm.enums.EnrollmentStatus;
import com.education.finalorm.exception.BusinessException;
import com.education.finalorm.exception.ResourceNotFoundException;
import com.education.finalorm.mapper.EnrollmentMapper;
import com.education.finalorm.repository.CourseRepository;
import com.education.finalorm.repository.EnrollmentRepository;
import com.education.finalorm.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EnrollmentService {
    private final EnrollmentRepository enrollmentRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final EnrollmentMapper enrollmentMapper;

    @Transactional
    public EnrollmentResponse enrollStudent(EnrollmentRequest request) {
        Student student = studentRepository.findById(request.getStudentId())
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));
        Course course = courseRepository.findById(request.getCourseId())
                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));
        if (enrollmentRepository.findByStudentIdAndCourseId(student.getId(), course.getId()).isPresent()) {
            throw new BusinessException("Student already enrolled in this course");
        }
        Enrollment enrollment = enrollmentMapper.toEntity(request);
        enrollment.setStudent(student);
        enrollment.setCourse(course);
        enrollment.setEnrollDate(LocalDateTime.now());
        enrollment.setStatus(EnrollmentStatus.ACTIVE);
        enrollment = enrollmentRepository.save(enrollment);
        return enrollmentMapper.toResponse(enrollment);
    }

    @Transactional(readOnly = true)
    public List<EnrollmentResponse> getEnrollmentsByStudent(UUID studentId) {
        return enrollmentMapper.toResponseList(enrollmentRepository.findByStudentId(studentId));
    }

    @Transactional
    public EnrollmentResponse updateEnrollmentStatus(UUID id, EnrollmentStatus status) {
        Enrollment enrollment = enrollmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Enrollment not found with id: " + id));
        enrollment.setStatus(status);
        enrollment = enrollmentRepository.save(enrollment);
        return enrollmentMapper.toResponse(enrollment);
    }

    @Transactional
    public void dropEnrollment(UUID id) {
        Enrollment enrollment = enrollmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Enrollment not found with id: " + id));
        enrollment.setStatus(EnrollmentStatus.DROPPED);
        enrollmentRepository.save(enrollment);
    }
}
