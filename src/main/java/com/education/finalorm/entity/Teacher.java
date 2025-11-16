package com.education.finalorm.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "teachers")
public class Teacher extends User {

    @Column(name = "academic_title", length = 100)
    private String academicTitle;

    @OneToMany(mappedBy = "teacher", fetch = FetchType.LAZY)
    private List<Course> courses = new ArrayList<>();
}