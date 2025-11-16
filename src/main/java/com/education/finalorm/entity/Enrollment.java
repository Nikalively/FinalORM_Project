package com.education.finalorm.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import com.education.finalorm.enums.EnrollmentStatus;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "enrollments", uniqueConstraints = @UniqueConstraint(columnNames = {"student_id", "course_id"}))
public class Enrollment extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private EnrollmentStatus status;

    @Column(name = "enroll_date", nullable = false)
    private LocalDateTime enrollDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;
}
