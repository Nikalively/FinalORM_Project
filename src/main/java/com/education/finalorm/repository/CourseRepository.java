package com.education.finalorm.repository;

import com.education.finalorm.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CourseRepository extends JpaRepository<Course, UUID> {
    List<Course> findByTeacherId(UUID teacherId);

    List<Course> findByCategoryId(UUID categoryId);

    @Query("SELECT c FROM Course c JOIN c.tags t WHERE t.name = :tagName")
    List<Course> findByTagName(@Param("tagName") String tagName);

    Optional<Course> findByTitleIgnoreCase(String title);

    @Query("SELECT c FROM Course c WHERE LOWER(c.title) LIKE LOWER(CONCAT('%', :search, '%')) OR LOWER(c.description) LIKE LOWER(CONCAT('%', :search, '%'))")
    List<Course> searchCourses(@Param("search") String search);
}
