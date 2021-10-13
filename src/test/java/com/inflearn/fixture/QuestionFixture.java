package com.inflearn.fixture;

import com.inflearn.lecture.questions.domain.Question;

public class QuestionFixture {


    public static final String CONTENT = "CONTENT";

    public static Question 강의_질문_active() {
        return Question.builder()
//                .title()
                .content(CONTENT)
//                .questionStatus()
//                .lecture()
                .active(true)
                .build();
    }
}
