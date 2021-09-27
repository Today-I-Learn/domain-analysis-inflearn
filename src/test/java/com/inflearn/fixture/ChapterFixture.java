package com.inflearn.fixture;

import com.inflearn.lecture.domain.Chapter;

import java.time.Duration;

public class ChapterFixture {
    public static final Long ID = 1L;
    public static final String TITLE = "lombok";
    public static final String VIDEO = "http://project.lombok.org";
    public static final String DOCUMENT = "http://project.lombok.org/reference.pdf";
    public static final Duration PLAY_TIME = Duration.ZERO;
    public static boolean DISPLAYED = false;

    public static Chapter 챕터() {
        return Chapter.builder()
                .id(ID)
                .title(TITLE)
                .video(VIDEO)
                .document(DOCUMENT)
                .playTime(PLAY_TIME)
                .build();
    }

    public static Chapter 타이틀이_없는_챕터() {
        return Chapter.builder()
                .id(ID)
                .video(VIDEO)
                .document(DOCUMENT)
                .playTime(PLAY_TIME)
                .build();
    }
}
