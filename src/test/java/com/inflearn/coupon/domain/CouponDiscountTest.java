package com.inflearn.coupon.domain;

import com.inflearn.fixture.CouponFixture;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;


@DisplayName("쿠폰 할인가(CouponDiscount)는")
class CouponDiscountTest {

    @DisplayName("쿠폰 타입과 할인 값(금액,비율)을 가진다.")
    @Test
    void create() {
        final CouponType couponType = CouponType.PRICE;
        final int value = 1000;

        CouponDiscount couponDiscount = new CouponDiscount(couponType, value);

        assertThat(couponDiscount).isEqualTo(new CouponDiscount(couponType, value));
    }

    @DisplayName("0 이하의 할인값은 IllegalArgumentException이 발생한다")
    @ParameterizedTest
    @ValueSource(ints = {-1})
    void create_invalid_value(final int value) {
        ThrowableAssert.ThrowingCallable throwingCallable = () -> new CouponDiscount(CouponType.RATIO, value);

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(throwingCallable);
    }

    @DisplayName("비율 할인값을 계산할 수 있다.")
    @Test
    void discount_ratio() {
        CouponDiscount couponDiscount = CouponFixture.QUARTER_DISCOUNT;
        final BigDecimal price = BigDecimal.valueOf(10_000);

        assertThat(couponDiscount.discount(price).toBigInteger()).isEqualTo(BigDecimal.valueOf(7_500).toBigInteger());
    }

    @DisplayName("금액 할인값을 계산할 수 있다.")
    @Test
    void discount_price() {
        CouponDiscount couponDiscount = CouponFixture.TEN_THOUSAND_DISCOUNT;
        final BigDecimal price = BigDecimal.valueOf(20_000);

        final BigDecimal result = couponDiscount.discount(price);
        assertThat(result).isEqualTo(BigDecimal.valueOf(10_000));
    }

    @DisplayName("할인할때 결과값이 음수면 0을 반환한다.")
    @Test
    void discount_under_zero() {
        CouponDiscount couponDiscount = CouponFixture.TEN_THOUSAND_DISCOUNT;

        final BigDecimal price = BigDecimal.valueOf(10);

        Assertions.assertThat(couponDiscount.discount(price)).isEqualTo(BigDecimal.ZERO);
    }
}
