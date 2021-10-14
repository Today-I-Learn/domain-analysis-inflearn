package com.inflearn.fixture;

import com.inflearn.lecture.questions.domain.Question;

public class QuestionFixture {

    public static final String CONTENT = "내용";
    public static final String TITLE = "제목";

    public static Question 미해결_강의_질문_active() {
        return new Question(TITLE, CONTENT, LectureFixture.승인_완료된_강의(), MemberFixture.회원());
    }
}
