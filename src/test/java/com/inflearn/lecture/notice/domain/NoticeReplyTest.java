package com.inflearn.lecture.notice.domain;


import com.inflearn.fixture.NoticeReplyFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
/*
## 강의 공지글 댓글

        - 공지글 댓글을 생성할 수 있다.
        - 댓글은 지식공유자와 수강생이 작성할 수 있다.
*/

@DisplayName("강의 공지글 댓글은")
public class NoticeReplyTest {

    @DisplayName("생성될 수 있다.")
    @Test
    public void create() {
        NoticeReply noticeReply = NoticeReplyFixture.강의_공지글_댓글_active();

        assertThat(noticeReply.getContent()).isEqualTo(NoticeReplyFixture.CONTENT);
    }

    @DisplayName("수정될 수 있다.")
    @Test
    public void update() {
        NoticeReply noticeReply = NoticeReplyFixture.강의_공지글_댓글_active();
        final String content = "바꾼내용";

        NoticeReply request = NoticeReply.builder()
                .content(content)
                .build();

        noticeReply.update(request);

        assertThat(noticeReply.getContent()).isEqualTo(content);
    }

    @DisplayName("삭제될 수 있다.")
    @Test
    public void delete() {
        NoticeReply noticeReply = NoticeReplyFixture.강의_공지글_댓글_active();

        noticeReply.remove();

        assertThat(noticeReply.isActive()).isFalse();
    }
}
