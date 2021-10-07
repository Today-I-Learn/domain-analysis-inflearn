package com.inflearn.lecture.domain;

import com.inflearn.fixture.ChapterFixture;
import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("챕터(Chapter)는")
public class ChapterTest {

    @DisplayName("생성할 수 있다.")
    @Test
    void create() {
        Chapter chapter = ChapterFixture.챕터();

        assertAll(
                () -> assertThat(chapter.getTitle()).isEqualTo(ChapterFixture.TITLE),
                () -> assertThat(chapter.getVideo()).isEqualTo(ChapterFixture.VIDEO),
                () -> assertThat(chapter.getDocument()).isEqualTo(ChapterFixture.DOCUMENT),
                () -> assertThat(chapter.getPlayTime()).isEqualTo(ChapterFixture.PLAY_TIME)
        );
    }

    @DisplayName("타이틀은 비어있을 수 없다.")
    @Test
    void create_without_title() {
        ThrowingCallable throwingCallable = ChapterFixture::타이틀이_없는_챕터;

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(throwingCallable);
    }

    @DisplayName("수정 될 수 있다")
    @Test
    void update() {
        Chapter chapter = ChapterFixture.챕터();
        chapter.update(ChapterFixture.변경된_챕터());

        assertAll(
                () -> assertThat(chapter.getTitle()).isEqualTo(ChapterFixture.CHANGED_TITLE),
                () -> assertThat(chapter.getVideo()).isEqualTo(ChapterFixture.CHANGED_VIDEO),
                () -> assertThat(chapter.getDocument()).isEqualTo(ChapterFixture.CHANGED_DOCUMENT),
                () -> assertThat(chapter.getPlayTime()).isEqualTo(ChapterFixture.CHANGED_PLAY_TIME)
        );
    }

}
