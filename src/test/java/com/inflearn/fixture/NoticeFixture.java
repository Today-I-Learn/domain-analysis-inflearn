package com.inflearn.fixture;

import com.inflearn.lecture.notice.domain.Notice;

public class NoticeFixture {

    public static final String TITLE = "제목";
    public static final String CONTENT = "내용";

    public static Notice 강의_공지글_active() {
        return Notice.builder()
                .instructor(MemberFixture.지식공유자())
                .title(TITLE)
                .content(CONTENT)
                .lecture(LectureFixture.승인_완료된_강의())
                .instructor(MemberFixture.지식공유자())
                .build();
    }
}
