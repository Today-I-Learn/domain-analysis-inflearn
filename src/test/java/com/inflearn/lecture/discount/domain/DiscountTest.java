package com.inflearn.lecture.discount.domain;

import com.inflearn.fixture.DiscountFixture;
import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertAll;


@DisplayName("할인(Discount)는")
class DiscountTest {
    @DisplayName("생성할 수 있다.")
    @Test
    void create() {
        Discount discount = DiscountFixture.할인();

        assertAll(
                () -> assertThat(discount.getTitle()).isEqualTo(DiscountFixture.TITLE),
                () -> assertThat(discount.getRatio()).isEqualTo(DiscountFixture.RATIO),
                () -> assertThat(discount.getStartTime()).isEqualTo(DiscountFixture.START_TIME),
                () -> assertThat(discount.getEndTime()).isEqualTo(DiscountFixture.END_TIME)
        );
    }

    @DisplayName("할인율이 0 미만이면 IllegalArgumentException이 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = {-3})
    void create_invalid_under_ratio(final int ratio) {
        ThrowableAssert.ThrowingCallable throwingCallable = () -> DiscountFixture.할인율(ratio);

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(throwingCallable);
    }

    @DisplayName("할인율이 100 초과하면 IllegalArgumentException이 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = {110})
    void create_invalid_over_ratio(final int ratio) {
        ThrowableAssert.ThrowingCallable throwingCallable = () -> DiscountFixture.할인율(ratio);

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(throwingCallable);
    }

    @DisplayName("제목이 비어있으면 IllegalArgumentException이 발생한다.")
    @ParameterizedTest
    @NullAndEmptySource
    void create_invalid_title(final String title) {
        ThrowableAssert.ThrowingCallable throwingCallable = () -> new Discount(title, DiscountFixture.RATIO, DiscountFixture.START_TIME, DiscountFixture.END_TIME);

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(throwingCallable);
    }

    @DisplayName("시작시간이 생성 시점보다 이전 날짜면 IllegalArgumentException이 발생한다.")
    @Test
    void create_invalid_startTime() {
        ThrowableAssert.ThrowingCallable throwingCallable = () -> new Discount(DiscountFixture.TITLE, DiscountFixture.RATIO, DiscountFixture.LONG_TIME_AGO, DiscountFixture.END_TIME);

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(throwingCallable);
    }

    @DisplayName("종료날짜가 시작날짜보다 이전 날짜면 IllegalArgumentException이 발생한다.")
    @Test
    void create_invalid_endTime() {
        ThrowableAssert.ThrowingCallable throwingCallable = () -> new Discount(DiscountFixture.TITLE, DiscountFixture.RATIO, DiscountFixture.START_TIME, DiscountFixture.LONG_TIME_AGO);

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(throwingCallable);
    }

    // 할인은 종료되었는지 확인할 수 있다.
    @DisplayName("종료되었는지 확인할 수 있다.")
    @Test
    void isFinished() {
        // given
        Discount discount = DiscountFixture.종료된_할인();

        // then
        assertThat(discount.getEndTime()).isBefore(LocalDateTime.now());
    }

    // 할인은 종료시킬 수 있다.
    @DisplayName("종료시킬 수 있다.")
    @Test
    void finish() {
        // given
        Discount discount = DiscountFixture.할인();

        // when
        discount.finish();

        // then
        assertThat(discount.getEndTime()).isBefore(LocalDateTime.now());
    }

}
