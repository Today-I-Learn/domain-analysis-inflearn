package com.inflearn.lecture.notice.domain;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertAll;

import com.inflearn.fixture.LectureFixture;
import com.inflearn.fixture.MemberFixture;
import com.inflearn.fixture.NoticeFixture;
import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
/*
## 강의 공지글

        - 공지글은 해당 강의의 지식공유자만 관리(생성, 수정, 삭제)할 수 있다.
        - 공지글을 생성할 수 있다.
        - 강의 ID, 공지글 제목, 내용이 필요하다.
        - 공지글을 삭제할 수 있다.
        - 공지글을 수정할 수 있다.


        - 공지글에 댓글을 생성할 수 있다.
        - 댓글은 지식공유자와 수강생이 작성할 수 있다.
*/

@DisplayName("강의 공지글은")
public class NoticeTest {

    //지식공유자가 포함되어야 하는지?
    @DisplayName("생성될 수 있다.")
    @Test
    public void create() {
        Notice notice = NoticeFixture.강의_공지글_active();

        assertAll(
                () -> assertThat(notice.getTitle()).isEqualTo(NoticeFixture.TITLE),
                () -> assertThat(notice.getContent()).isEqualTo(NoticeFixture.CONTENT)
        );
    }

    @DisplayName("타이틀은 비어있을 수 없다.")
    @NullAndEmptySource
    @ParameterizedTest
    void createFailByTitle(String title) {
        ThrowableAssert.ThrowingCallable throwingException = () -> new Notice(title, "내용", LectureFixture.승인_완료된_강의(), MemberFixture.지식공유자());

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(throwingException);
    }

    @DisplayName("내용은 비어있을 수 없다.")
    @NullAndEmptySource
    @ParameterizedTest
    void createFailByContent(String content) {
        ThrowableAssert.ThrowingCallable throwingException = () -> new Notice("타이틀", content, LectureFixture.승인_완료된_강의(), MemberFixture.지식공유자());

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(throwingException);
    }

    @DisplayName("강의는 비어있을 수 없다.")
    @Test
    void createFailByLecture() {
        ThrowableAssert.ThrowingCallable throwingException = () -> Notice.builder()
                .title("제목")
                .content("내용")
                .instructor(MemberFixture.지식공유자())
                .build();

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(throwingException);
    }

    @DisplayName("지식공유자는 비어있을 수 없다.")
    @Test
    void createFailByInstructor() {
        ThrowableAssert.ThrowingCallable throwingException = () -> Notice.builder()
                .title("제목")
                .content("내용")
                .lecture(LectureFixture.승인_완료된_강의())
                .build();

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(throwingException);
    }

    @DisplayName("지식공유자만 공지 생성이 가능하다.")
    @Test
    void createFailByNonInstructor() {
        ThrowableAssert.ThrowingCallable throwingException = () -> Notice.builder()
                .title("제목")
                .content("내용")
                .lecture(LectureFixture.승인_완료된_강의())
                .instructor(MemberFixture.회원())
                .build();

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(throwingException);
    }

    @DisplayName("수정될 수 있다.")
    @Test
    public void update() {
        Notice notice = NoticeFixture.강의_공지글_active();
        final String title = "바꾼제목";
        final String content = "바꾼내용";

        Notice request = Notice.builder()
                .title(title)
                .content(content)
                .lecture(LectureFixture.승인_완료된_강의())
                .instructor(MemberFixture.지식공유자())
                .build();

        notice.update(request);

        assertAll(
                () -> assertThat(notice.getTitle()).isEqualTo(title),
                () -> assertThat(notice.getContent()).isEqualTo(content)
        );
    }

    @DisplayName("삭제될 수 있다.")
    @Test
    public void delete() {
        Notice notice = NoticeFixture.강의_공지글_active();

        notice.remove();

        assertThat(notice.isActive()).isFalse();
    }
}
