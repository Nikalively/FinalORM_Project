package com.education.finalorm.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "tags")
public class Tag extends BaseEntity {

    @Column(name = "name", unique = true, nullable = false, length = 50)
    private String name;

    @Column(name = "slug", unique = true, nullable = false, length = 50)
    private String slug;

    @ManyToMany(mappedBy = "tags", fetch = FetchType.LAZY)
    private Set<Course> courses = new HashSet<>();
}