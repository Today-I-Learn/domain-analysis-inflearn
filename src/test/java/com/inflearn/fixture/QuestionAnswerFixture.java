package com.inflearn.fixture;

import com.inflearn.lecture.questions.domain.QuestionAnswer;

public class QuestionAnswerFixture {
    public static QuestionAnswer 답변_active() {
        return QuestionAnswer.builder()
                .active(true)
                .build();
    }
}
