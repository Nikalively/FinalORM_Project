package com.education.finalorm.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "lessons")
public class Lesson extends BaseEntity {

    @Column(name = "title", nullable = false)
    private String title;

    @Lob
    @Column(name = "content")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "module_id", nullable = false)
    private Module module;

    @OneToMany(mappedBy = "lesson", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Assignment> assignments = new ArrayList<>();
}
