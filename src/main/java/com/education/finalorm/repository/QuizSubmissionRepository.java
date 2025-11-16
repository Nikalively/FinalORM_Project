package com.education.finalorm.repository;

import com.education.finalorm.entity.QuizSubmission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface QuizSubmissionRepository extends JpaRepository<QuizSubmission, UUID> {
    Optional<QuizSubmission> findByStudentIdAndQuizId(UUID studentId, UUID quizId);

    List<QuizSubmission> findByStudentId(UUID studentId);

    List<QuizSubmission> findByQuizId(UUID quizId);
}
