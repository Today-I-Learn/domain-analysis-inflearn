package com.inflearn.fixture;

import com.inflearn.lecture.domain.Lecture;
import com.inflearn.lecture.domain.Price;

public class LectureFixture {

    private static final Long ID = 1L;
    private static final String TITLE = "제목";
    private static final Price PRICE = new Price(3000);
    private static final String INTRODUCE = "강의를 소개합니다!";

    public static Lecture 강의() {
        return null;
    }

    public static Lecture 무료강의() {
        return null;
    }
}
