package com.inflearn.fixture;

import com.inflearn.lecture.notice.domain.NoticeReply;

public class NoticeReplyFixture {
    public static final String CONTENT = "내용";

    public static NoticeReply 강의_공지글_댓글_active() {
        return NoticeReply.builder()
                .content(CONTENT)
                .member(MemberFixture.회원())
                .active(true)
                .build();
    }
}
