package com.education.finalorm.repository;

import com.education.finalorm.entity.Submission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface SubmissionRepository extends JpaRepository<Submission, UUID> {
    Optional<Submission> findByStudentIdAndAssignmentId(UUID studentId, UUID assignmentId);

    List<Submission> findByStudentId(UUID studentId);

    List<Submission> findByAssignmentId(UUID assignmentId);
}
