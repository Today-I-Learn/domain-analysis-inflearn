package com.inflearn.coupon.domain;

import com.inflearn.fixture.CouponFixture;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("쿠폰(Coupon)은")
class CouponTest {

    @DisplayName("생성할 수 있다.")
    @Test
    void create() {
//        Coupon coupon = CouponFixture.
    }

    @DisplayName("할인가를 계산할 수 있다.")
    @Test
    void discount() {
        Coupon coupon = CouponFixture.부활_가능_쿠폰(CouponFixture.TEN_THOUSAND_DISCOUNT);
        final BigDecimal price = BigDecimal.valueOf(20_000);

        final BigDecimal result = coupon.discount(price);
        assertThat(result).isEqualTo(BigDecimal.valueOf(10_000));
    }

    @DisplayName("부활 시킬 수 있다.")
    @Test
    void revive() {
        Coupon coupon = CouponFixture.부활_가능_쿠폰(CouponFixture.TEN_THOUSAND_DISCOUNT);

        Assertions.assertThat(coupon.isRevivable()).isTrue();
    }

//    @DisplayName("")

}
