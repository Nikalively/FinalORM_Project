package com.education.finalorm.service;

import com.education.finalorm.dto.request.ReviewRequest;
import com.education.finalorm.dto.response.ReviewResponse;
import com.education.finalorm.entity.Course;
import com.education.finalorm.entity.CourseReview;
import com.education.finalorm.entity.Student;
import com.education.finalorm.enums.EnrollmentStatus;
import com.education.finalorm.exception.BusinessException;
import com.education.finalorm.exception.ResourceNotFoundException;
import com.education.finalorm.mapper.ReviewMapper;
import com.education.finalorm.repository.CourseRepository;
import com.education.finalorm.repository.CourseReviewRepository;
import com.education.finalorm.repository.EnrollmentRepository;
import com.education.finalorm.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final CourseReviewRepository reviewRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final EnrollmentRepository enrollmentRepository;
    private final ReviewMapper reviewMapper;

    @Transactional
    public ReviewResponse createReview(ReviewRequest request) {
        Student student = studentRepository.findById(request.getStudentId())
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));
        Course course = courseRepository.findById(request.getCourseId())
                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));
        Enrollment enrollment = enrollmentRepository.findByStudentIdAndCourseId(student.getId(), course.getId())
                .orElseThrow(() -> new BusinessException("Student not enrolled in this course"));
        if (enrollment.getStatus() != EnrollmentStatus.COMPLETED) {
            throw new BusinessException("Can only review completed courses");
        }
        if (reviewRepository.findByStudentIdAndCourseId(student.getId(), course.getId()).isPresent()) {
            throw new BusinessException("Review already submitted");
        }
        CourseReview review = reviewMapper.toEntity(request);
        review.setStudent(student);
        review.setCourse(course);
        review = reviewRepository.save(review);
        return reviewMapper.toResponse(review);
    }

    @Transactional(readOnly = true)
    public List<ReviewResponse> getReviewsByCourse(UUID courseId) {
        return reviewMapper.toResponseList(reviewRepository.findByCourseId(courseId));
    }

    @Transactional(readOnly = true)
    public Double getAverageRating(UUID courseId) {
        return reviewRepository.findAverageRatingByCourseId(courseId);
    }
}
