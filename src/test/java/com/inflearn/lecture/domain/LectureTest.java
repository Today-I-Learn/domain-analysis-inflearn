package com.inflearn.lecture.domain;

import com.inflearn.fixture.LectureFixture;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("강의(Lecture)는")
public class LectureTest {
  /*
    - 지식공유자는 강의 등록을 신청할 수 있다. ok
      - 강의 제목, 카테고리, 난이도, 교육과정, 금액이 필요하다.

    - 강의를 승인할 수 있다. ok

    - 강의를 수정할 수 있다.
      - 어디까지 수정할수 있는지 범위를 지정해야하지 않을까용??
      - 그리고 제목 요소에는 null값이 안된다는 검증을 한번 더 거쳐야할것 같군요 ㅎ
      - 초과근무 멈춰
      - 해당 강의의 지식공유자, 관리자만 수정할 수 있다.

    - 강의 목록을 조회할 수 있다.
    - 강의 상세 내용을 조회할 수 있다.
    - 해당 강의의 수강생과 지식공유자는 강의 구성원 이다.

  */

    @DisplayName("강의를 생성할 수 있다.")
    @Test
    void create() {
        // 강의 식별자, 제목, 가격(값객체), 카테고리(이넘^^), 난이도(이넘^^),
        // Section(X) -> Chapter(이건 테이블 따로 빼야함), 태그(String으로? #자바 #스프링), 소개, 지식공유자
        Lecture lecture = LectureFixture.승인_대기중_강의();

        assertAll(
                () -> Assertions.assertThat(lecture.getTitle()).isEqualTo(LectureFixture.TITLE),
                () -> Assertions.assertThat(lecture.getPrice()).isEqualTo(LectureFixture.PRICE),
                () -> Assertions.assertThat(lecture.getIntroduce()).isEqualTo(LectureFixture.INTRODUCE),
                () -> Assertions.assertThat(lecture.getInstructor()).isEqualTo(LectureFixture.INSTRUCTOR),
                () -> Assertions.assertThat(lecture.getChapters()).isEqualTo(LectureFixture.CHAPTERS),
                () -> Assertions.assertThat(lecture.getTags()).isEqualTo(LectureFixture.TAGS)
        );
    }

    @DisplayName("강의는 승인 될 수 있다.")
    @Test
    void approve() {
        Lecture lecture = LectureFixture.승인_대기중_강의();

        lecture.approve();

        Assertions.assertThat(lecture.getApproved()).isEqualTo(Approval.APPROVED);
    }

    @DisplayName("이미 승인 된 강의를 승인하면 IllegalStateException 을 던진다.")
    @Test
    void approve_fail() {
        Lecture lecture = LectureFixture.승인_완료된_강의();

        ThrowableAssert.ThrowingCallable throwingCallable = lecture::approve;

        assertThatExceptionOfType(IllegalStateException.class)
                .isThrownBy(throwingCallable);
    }
}
