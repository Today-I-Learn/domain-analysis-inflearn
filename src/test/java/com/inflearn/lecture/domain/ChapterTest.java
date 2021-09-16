package com.inflearn.lecture.domain;

import com.inflearn.fixture.ChapterFixture;
import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class ChapterTest {

    @DisplayName("챕터를 생성할 수 있다.")
    @Test
    void create() {
        Chapter chapter = ChapterFixture.챕터();

//        assertThat(chapter).isEqualTo(new Chapter("오리엔테이션", "url", "url", time));
    }

    @DisplayName("타이틀은 비어있을 수 없다.")
    @Test
    void create_without_title() {
        ThrowingCallable throwingCallable = ChapterFixture::타이틀이_없는_챕터;

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(throwingCallable);
    }
}
