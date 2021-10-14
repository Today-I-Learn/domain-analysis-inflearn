package com.inflearn.lecture.questions.domain;

import com.inflearn.fixture.LectureFixture;
import com.inflearn.fixture.MemberFixture;
import com.inflearn.fixture.QuestionAnswerFixture;
import com.inflearn.fixture.QuestionFixture;
import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

/*
## 강의 질문

        - 질문과 답변은 강의 구성원만 관리(생성, 수정, 삭제)할 수 있다.
        - 질문을 생성할 수 있다.
        - 강의 ID, 질문 제목, 내용이 올바르지 않으면 질문을 생성할 수 없다.
        - 질문을 수정할 수 있다.
        - 질문을 삭제할 수 있다.
        - 답변을 등록할 수 있다.
        - 답변은 수강생과 지식공유자가 작성할 수 있다.
*/

@DisplayName("강의 질문은")
class QuestionTest {

    @DisplayName("생성될 수 있다.")
    @Test
    public void create() {
        Question question = QuestionFixture.미해결_강의_질문_active();

        assertThat(question.getContent()).isEqualTo(QuestionFixture.CONTENT);
    }

    @DisplayName("타이틀은 비어있을 수 없다.")
    @NullAndEmptySource
    @ParameterizedTest
    void createFailByTitle(String title) {
        ThrowingCallable throwingException = () -> new Question(title, "내용", LectureFixture.승인_완료된_강의(), MemberFixture.회원());

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(throwingException);
    }

    @DisplayName("내용은 비어있을 수 없다.")
    @NullAndEmptySource
    @ParameterizedTest
    void createFailByContent(String content) {
        ThrowingCallable throwingException = () -> new Question("타이틀", content, LectureFixture.승인_완료된_강의(), MemberFixture.회원());

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(throwingException);
    }

    @DisplayName("강의는 비어있을 수 없다.")
    @Test
    void createFailByLecture() {
        ThrowingCallable throwingException = () -> Question.builder()
                .title("제목")
                .content("내용")
                .member(MemberFixture.회원())
                .build();

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(throwingException);
    }

    @DisplayName("질문자는 비어있을 수 없다.")
    @Test
    void createFailByMember() {
        ThrowingCallable throwingException = () -> Question.builder()
                .title("제목")
                .content("내용")
                .lecture(LectureFixture.승인_완료된_강의())
                .build();

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(throwingException);
    }

    @DisplayName("수정될 수 있다.")
    @Test
    public void update() {
        Question question = QuestionFixture.미해결_강의_질문_active();
        final String content = "바꾼내용";
        final String title = "바꾼제목";

        Question request = Question.builder()
                .title(title)
                .content(content)
                .lecture(LectureFixture.승인_완료된_강의())
                .member(MemberFixture.회원())
                .build();

        question.update(request);

        assertThat(question.getTitle()).isEqualTo(title);
        assertThat(question.getContent()).isEqualTo(content);
    }

    @DisplayName("삭제될 수 있다.")
    @Test
    public void delete() {
        Question question = QuestionFixture.미해결_강의_질문_active();

        question.remove();

        assertThat(question.isActive()).isFalse();
    }

    @DisplayName("질문 상태가 변경될 수 있다.")
    @Test
    public void updateQuestionStatus() {
        Question question = QuestionFixture.미해결_강의_질문_active();

        question.updateStatus(QuestionStatus.SOLVED);

        assertThat(question.getQuestionStatus()).isEqualTo(QuestionStatus.SOLVED);
    }

    @DisplayName("현재 상태와 동일한 질문 상태로는 변경될 수 없다.")
    @Test
    public void updateQuestionStatusFail() {
        Question question = QuestionFixture.미해결_강의_질문_active();

        ThrowingCallable throwingException = () -> question.updateStatus(QuestionStatus.UNSOLVED);

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(throwingException);
    }

    @DisplayName("답변이 등록될 수 있다.")
    @Test
    public void addAnswer() {
        Question question = QuestionFixture.미해결_강의_질문_active();

        int preCount = question.getAnswerCount();

        question.addAnswer(QuestionAnswerFixture.답변_active());

        assertThat(preCount + 1).isEqualTo(question.getAnswerCount());
    }

}
