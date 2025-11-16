package com.education.finalorm.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import com.education.finalorm.enums.SubmissionStatus;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "submissions", uniqueConstraints = @UniqueConstraint(columnNames = {"student_id", "assignment_id"}))
public class Submission extends BaseEntity {

    @Column(name = "content", length = 2000)
    private String content;

    @Column(name = "file_url", length = 500)
    private String fileUrl;

    @Column(name = "submitted_at", nullable = false)
    private LocalDateTime submittedAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private SubmissionStatus status;

    @Column(name = "score")
    private Double score;

    @Column(name = "feedback", length = 1000)
    private String feedback;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assignment_id", nullable = false)
    private Assignment assignment;
}
