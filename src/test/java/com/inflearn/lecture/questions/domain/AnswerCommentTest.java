package com.inflearn.lecture.questions.domain;


import com.inflearn.fixture.AnswerCommentFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("강의 질문 답변의 댓글은")
public class AnswerCommentTest {

    @DisplayName("생성될 수 있다.")
    @Test
    public void create() {
        AnswerComment answerComment = AnswerCommentFixture.강의_질문_답변_댓글_active();

        assertThat(answerComment.getContent()).isEqualTo(AnswerCommentFixture.CONTENT);
    }

    @DisplayName("수정될 수 있다.")
    @Test
    public void update() {
        AnswerComment answerComment = AnswerCommentFixture.강의_질문_답변_댓글_active();
        final String content = "바꾼내용";

        AnswerComment request = AnswerComment.builder()
                .content(content)
                .build();

        answerComment.update(request);

        assertThat(answerComment.getContent()).isEqualTo(content);
    }

    @DisplayName("삭제될 수 있다.")
    @Test
    public void delete() {
        AnswerComment answerComment = AnswerCommentFixture.강의_질문_답변_댓글_active();

        answerComment.remove();

        assertThat(answerComment.isActive()).isFalse();
    }
}
