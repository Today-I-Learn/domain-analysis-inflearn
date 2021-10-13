package com.inflearn.lecture.questions.domain;

import com.inflearn.lecture.domain.Lecture;
import com.inflearn.member.domain.Member;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    private boolean active;

    @Enumerated(EnumType.STRING)
    private QuestionStatus questionStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    private Lecture lecture;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(
            name = "question_answer_id",
            nullable = false,
            columnDefinition = "bigint(20)",
            foreignKey = @ForeignKey(name = "fk_question_answer_to_questions")
    )
    private List<QuestionAnswer> questionAnswer;
}
