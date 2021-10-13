package com.inflearn.lecture.questions.domain;

import com.inflearn.fixture.NoticeCommentFixture;
import com.inflearn.fixture.QuestionFixture;
import com.inflearn.lecture.notice.domain.NoticeComment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

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
        Question lectureNoticeComment = QuestionFixture.강의_질문_active();

        assertThat(lectureNoticeComment.getContent()).isEqualTo(QuestionFixture.CONTENT);
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
