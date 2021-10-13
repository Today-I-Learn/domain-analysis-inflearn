package com.inflearn.lecture.questions.domain;

import com.inflearn.member.domain.Member;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
public class QuestionAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    private boolean active;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(
            name = "question_comment_id",
            nullable = false,
            columnDefinition = "bigint(20)",
            foreignKey = @ForeignKey(name = "fk_question_comment_to_questions")
    )
    private List<AnswerComment> comment;

    public void update(QuestionAnswer questionAnswer) {
        this.content = questionAnswer.content;
    }

    public void remove() {
        this.active = false;
    }
}
