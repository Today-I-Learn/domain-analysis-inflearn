package com.inflearn.fixture;

import com.inflearn.lecture.domain.Chapter;

import java.time.Duration;

public class ChapterFixture {
    private static final Long ID = 1L;
    private static final String TITLE = "lombok";
    private static final String VIDEO = "http://project.lombok.org";
    private static final String DOCUMENT = "http://project.lombok.org/reference.pdf";
    private static final Duration TIME = Duration.ZERO;
    private static boolean DISPLAYED = false;

    public static Chapter 챕터() {
        return Chapter.builder()
                .id(ID)
                .title(TITLE)
                .video(VIDEO)
                .document(DOCUMENT)
                .playTime(TIME)
                .build();
    }

    public static Chapter 타이틀이_없는_챕터() {
        return Chapter.builder()
                .id(ID)
                .video(VIDEO)
                .document(DOCUMENT)
                .playTime(TIME)
                .build();
    }
}
