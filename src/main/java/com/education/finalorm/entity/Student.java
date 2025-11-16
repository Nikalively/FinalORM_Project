package com.education.finalorm.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import com.education.finalorm.enums.EnrollmentStatus;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "students")
public class Student extends User {

    @Column(name = "student_no", unique = true, nullable = false, length = 20)
    private String studentNo;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private EnrollmentStatus status;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "student_emails", joinColumns = @JoinColumn(name = "student_id"))
    @Column(name = "email")
    private Set<String> emails = new HashSet<>();

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "student_phones", joinColumns = @JoinColumn(name = "student_id"))
    @Column(name = "phone")
    private Set<String> phones = new HashSet<>();

    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY)
    private List<Enrollment> enrollments = new ArrayList<>();
}