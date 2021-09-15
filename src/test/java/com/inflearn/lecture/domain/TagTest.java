package com.inflearn.lecture.domain;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class TagTest {

    @DisplayName("태그를 생성할 수 있다.")
    @Test
    void create() {
        Tag tag = new Tag("HTML/CSS");

        Assertions.assertThat(tag).isEqualTo(new Tag("HTML/CSS"));
    }

    @DisplayName("태그는 비어있는 이름일 수 없다.")
    @NullAndEmptySource
    @ParameterizedTest
    void create_fail(String name) {
        ThrowableAssert.ThrowingCallable throwingCallable = () -> new Tag(name);

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(throwingCallable);
    }
}