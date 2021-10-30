package com.inflearn.fixture;

import com.inflearn.lecture.questions.domain.AnswerComment;

public class AnswerCommentFixture {
    public static final String CONTENT = "내용";

    public static AnswerComment 강의_질문_답변_댓글_active() {
        return AnswerComment.builder()
                .content(CONTENT)
                .member(MemberFixture.회원())
                .active(true)
                .build();
    }
}
