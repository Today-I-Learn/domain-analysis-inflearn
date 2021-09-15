package com.inflearn.lecture.domain;

import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import java.time.Instant;

import static com.inflearn.lecture.domain.Chapter.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class ChapterTest {

    private Instant time = Instant.now();

    private ChapterBuilder builder = Chapter.builder()
            .title("오리엔테이션")
            .video("url")
            .document("url")
            .playTime(time);

    @DisplayName("챕터를 생성할 수 있다.")
    @Test
    void create() {
        Chapter chapter = builder.build();

        assertThat(chapter).isEqualTo(new Chapter("오리엔테이션", "url", "url", time));
    }

    @DisplayName("타이틀은 비어있을 수 없다.")
    @NullAndEmptySource
    @ParameterizedTest
    void create(String title) {
        ThrowableAssert.ThrowingCallable throwingCallable = () -> builder().title(title).build();

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(throwingCallable);
    }

}