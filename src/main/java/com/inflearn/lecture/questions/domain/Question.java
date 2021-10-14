package com.inflearn.lecture.questions.domain;

import com.inflearn.lecture.domain.Lecture;
import com.inflearn.member.domain.Member;
import lombok.*;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
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
    private final List<QuestionAnswer> questionAnswer = new ArrayList<>();

    @Builder
    public Question(String title, String content, Lecture lecture, Member member, QuestionStatus questionStatus) {
        validateArgument(title, content, lecture, member);
        this.title = title;
        this.content = content;
        this.lecture = lecture;
        this.member = member;
        this.questionStatus = questionStatus;
        this.active = true;
    }

    public Question(String title, String content, Lecture lecture, Member member) {
        this(title, content, lecture, member, QuestionStatus.UNSOLVED);
    }

    private void validateArgument(String title, String content, Lecture lecture, Member member) {
        if (!StringUtils.hasText(title)) {
            throw new IllegalArgumentException("타이틀은 비어있을 수 없습니다.");
        }
        if (!StringUtils.hasText(content)) {
            throw new IllegalArgumentException("내용은 비어있을 수 없습니다.");
        }
        if (Objects.isNull(lecture)) {
            throw new IllegalArgumentException("강의는 비어있을 수 없습니다.");
        }
        if (Objects.isNull(member)) {
            throw new IllegalArgumentException("질문자는 비어있을 수 없다.");
        }
    }

    public void update(Question request) {
        this.title = request.title;
        this.content = request.content;
    }

    public void remove() {
        this.active = false;
    }

    public void updateStatus(QuestionStatus questionStatus) {
        if (this.questionStatus == questionStatus) {
            throw new IllegalArgumentException("동일한 상태로 변경할 수 없습니다.");
        }
        this.questionStatus = questionStatus;
    }

    public void addAnswer(QuestionAnswer questionAnswer) {
        this.questionAnswer.add(questionAnswer);
    }

    public int getAnswerCount() {
        return this.questionAnswer.size();
    }
}
