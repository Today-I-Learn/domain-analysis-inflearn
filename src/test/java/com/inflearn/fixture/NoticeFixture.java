package com.inflearn.fixture;

import com.inflearn.lecture.notice.domain.Notice;

public class NoticeFixture {

    public static final String TITLE = "제목";
    public static final String CONTENT = "내용";

    public static Notice 강의_공지글_active() {
        return Notice.builder()
                .title(TITLE)
                .content(CONTENT)
                .active(true)
                .build();
    }
}
