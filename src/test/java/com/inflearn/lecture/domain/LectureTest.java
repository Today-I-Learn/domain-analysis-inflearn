package com.inflearn.lecture.domain;

import com.inflearn.fixture.ChapterFixture;
import com.inflearn.fixture.LectureFixture;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("강의(Lecture)는")
public class LectureTest {
  /*
    - 지식공유자는 강의 등록을 신청할 수 있다. ok
      - 강의 제목, 카테고리, 난이도, 교육과정, 금액이 필요하다.

    - 강의를 승인할 수 있다. ok

    - 강의를 수정할 수 있다.
      * 선행조건 (Application에서의 기능 요구사항)

      * 후행조건
        - 강의 수강생에게 이메일을 발송한다.


      - 어디까지 수정할수 있는지 범위를 지정해야하지 않을까용??
      - 그리고 제목 요소에는 null값이 안된다는 검증을 한번 더 거쳐야할것 같군요 ㅎ
      - 해당 강의의 지식공유자, 관리자만 수정할 수 있다

    Lecture -> Chapter (1:N) Chapter (Lecture 연관관계의 주인) 도메인의 관점이 아니라 JPA by.김영한
    public void addChapter(Chapter chapter){
        chapters.add(chapter);
    }
    챕터 update -> 챕터에서
    챕터에 대한 reference 필요


    - 해당 강의의 수강생과 지식공유자는 강의 구성원(강의에 대한 지식공유자, 수강생을 함께 부르는 단어) 이다.
  */

    @DisplayName("생성할 수 있다.")
    @Test
    void create() {
        Lecture lecture = LectureFixture.승인_대기중_강의();

        assertAll(
                () -> Assertions.assertThat(lecture.getTitle()).isEqualTo(LectureFixture.TITLE),
                () -> Assertions.assertThat(lecture.getPrice()).isEqualTo(LectureFixture.PRICE),
                () -> Assertions.assertThat(lecture.getIntroduce()).isEqualTo(LectureFixture.INTRODUCE),
                () -> Assertions.assertThat(lecture.getInstructor()).isEqualTo(LectureFixture.INSTRUCTOR),
                () -> Assertions.assertThat(lecture.getChapters().size()).isEqualTo(LectureFixture.CHAPTERS.size()),
                () -> Assertions.assertThat(lecture.getTags().size()).isEqualTo(LectureFixture.TAGS.size())
        );
    }

    @DisplayName("승인 될 수 있다.")
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

    @DisplayName("수정 될 수 있다.")
    @Test
    void update() {
        Lecture lecture = LectureFixture.승인_완료된_강의();
        final String title = "바뀐이름";
        final Category category = new Category("바꾸려는 카테고리");
        final Price price = new Price(1000);
        final Level level = Level.EASY;
        final String introduce = "바꾸려는 소개";
        Lecture request = Lecture.builder()
                .title(title)
                .category(category)
                .price(price)
                .level(level)
                .introduce(introduce)
                .build();

        lecture.update(request);

        assertAll(
                () -> assertThat(lecture.getTitle()).isEqualTo(title),
                () -> assertThat(lecture.getCategory()).isEqualTo(category),
                () -> assertThat(lecture.getPrice()).isEqualTo(price),
                () -> assertThat(lecture.getLevel()).isEqualTo(level),
                () -> assertThat(lecture.getIntroduce()).isEqualTo(introduce)
        );
    }

    @DisplayName("챕터를 삭제할 수 있다.")
    @Test
    void remove() {
        Chapter chapter = ChapterFixture.챕터();
        Lecture lecture = LectureFixture.승인_완료된_강의(chapter);
        lecture.removeChapter(chapter);

        Assertions.assertThat(chapter.isActive()).isEqualTo(false);
    }

    @DisplayName("챕터를 추가할 수 있다.")
    @Test
    void add() {
        Chapter chapter = ChapterFixture.챕터();
        Lecture lecture = LectureFixture.승인_완료된_강의();
        lecture.addChapter(chapter);

        Assertions.assertThat(lecture.getChapters().size()).isEqualTo(2);
    }

}
