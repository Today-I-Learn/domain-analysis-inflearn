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

    @OneToMany
    @JoinColumn(name = "question_answer_id")
    private List<AnswerComment> comment;

    public void update(QuestionAnswer questionAnswer) {
        this.content = questionAnswer.content;
    }

    public void remove() {
        this.active = false;
    }
}
