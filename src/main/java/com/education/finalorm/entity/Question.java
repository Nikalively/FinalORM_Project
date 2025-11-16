package com.education.finalorm.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import com.education.finalorm.enums.QuestionType;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "questions")
public class Question extends BaseEntity {

    @Column(name = "text", nullable = false, length = 1000)
    private String text;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private QuestionType type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quiz_id", nullable = false)
    private Quiz quiz;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<QuestionOption> options = new ArrayList<>();
}
