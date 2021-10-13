package com.inflearn.fixture;

import com.inflearn.lecture.notice.domain.NoticeComment;

public class NoticeCommentFixture {
    public static final String CONTENT = "내용";

    public static NoticeComment 강의_공지글_댓글_active() {
        return NoticeComment.builder()
                .content(CONTENT)
                .member(MemberFixture.회원())
                .active(true)
                .build();
    }
}
