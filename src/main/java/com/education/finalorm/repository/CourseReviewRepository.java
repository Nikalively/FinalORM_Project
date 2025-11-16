package com.education.finalorm.repository;

import com.education.finalorm.entity.CourseReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CourseReviewRepository extends JpaRepository<CourseReview, UUID> {
    List<CourseReview> findByCourseId(UUID courseId);

    List<CourseReview> findByStudentId(UUID studentId);

    @Query("SELECT AVG(r.rating) FROM CourseReview r WHERE r.course.id = :courseId")
    Double findAverageRatingByCourseId(@Param("courseId") UUID courseId);
}
