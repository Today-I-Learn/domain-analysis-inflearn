package com.inflearn.lecture.review.domain;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@DisplayName("별점(Rating)은")
public class RatingTest {

    @DisplayName("생성할 수 있다.")
    @Test
    void create() {
        Rating rating = new Rating(5);

        Assertions.assertThat(rating).isEqualTo(new Rating(5));
    }

    @DisplayName("별점은 1~5점 사이어야한다.")
    @ParameterizedTest
    @ValueSource(ints = {-1, 6})
    void verify(int rate) {
        ThrowableAssert.ThrowingCallable throwingCallable = () -> new Rating(rate);

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(throwingCallable);
    }
}