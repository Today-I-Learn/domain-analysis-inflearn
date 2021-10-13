package com.inflearn.lecture.notice.domain;


import com.inflearn.fixture.NoticeCommentFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
/*
## 강의 공지글 댓글

        - 공지글 댓글을 생성할 수 있다.
        - 댓글은 지식공유자와 수강생이 작성할 수 있다.
*/

@DisplayName("강의 공지글 댓글은")
public class NoticeCommentTest {

    @DisplayName("생성될 수 있다.")
    @Test
    public void create() {
        NoticeComment noticeComment = NoticeCommentFixture.강의_공지글_댓글_active();

        assertThat(noticeComment.getContent()).isEqualTo(NoticeCommentFixture.CONTENT);
    }

    @DisplayName("수정될 수 있다.")
    @Test
    public void update() {
        NoticeComment noticeComment = NoticeCommentFixture.강의_공지글_댓글_active();
        final String content = "바꾼내용";

        NoticeComment request = NoticeComment.builder()
                .content(content)
                .build();

        noticeComment.update(request);

        assertThat(noticeComment.getContent()).isEqualTo(content);
    }

    @DisplayName("삭제될 수 있다.")
    @Test
    public void delete() {
        NoticeComment noticeComment = NoticeCommentFixture.강의_공지글_댓글_active();

        noticeComment.remove();

        assertThat(noticeComment.isActive()).isFalse();
    }
}
