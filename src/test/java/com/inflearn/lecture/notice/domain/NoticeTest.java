package com.inflearn.lecture.notice.domain;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import com.inflearn.fixture.NoticeFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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

    @DisplayName("수정될 수 있다.")
    @Test
    public void update() {
        Notice notice = NoticeFixture.강의_공지글_active();
        final String title = "바꾼제목";
        final String content = "바꾼내용";

        Notice request = Notice.builder()
                .title(title)
                .content(content)
                .build();

        notice.update(request);

        assertAll(
                () -> assertThat(notice.getTitle()).isEqualTo(title),
                () -> assertThat(notice.getContent()).isEqualTo(content)
        );
    }

/*    @DisplayName("좋아요 개수를 변경할 수 있다.")
    @Test
    public void updateLike() {
        // given
        Notice notice = NoticeFixture.강의_공지글_active();

    }*/

    @DisplayName("삭제될 수 있다.")
    @Test
    public void delete() {
        Notice notice = NoticeFixture.강의_공지글_active();

        notice.remove();

        assertThat(notice.isActive()).isFalse();
    }
}
