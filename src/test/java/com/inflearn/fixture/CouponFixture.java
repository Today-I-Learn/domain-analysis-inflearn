package com.inflearn.fixture;

import com.inflearn.coupon.domain.Coupon;
import com.inflearn.coupon.domain.CouponDiscount;
import com.inflearn.coupon.domain.CouponType;

import java.time.LocalDate;

public class CouponFixture {

    public static final Long ID = 1L;
    public static final String NAME = "인프런 공짜 쿠폰";
    public static final LocalDate PERIOD = LocalDate.of(2021, 10, 30);
    public static final CouponDiscount TEN_THOUSAND_DISCOUNT = new CouponDiscount(CouponType.PRICE, 10_000);
    public static final CouponDiscount QUARTER_DISCOUNT =
            new CouponDiscount(CouponType.RATIO, 25);

    public static Coupon 쿠폰(final boolean revivable, final CouponDiscount couponDiscount) {
        return Coupon.builder()
                .id(ID)
                .name(NAME)
                .period(PERIOD)
                .couponDiscount(couponDiscount)
                .revivable(revivable)
                .build();
    }

    public static Coupon 부활_가능_쿠폰(final CouponDiscount discount) {
        return 쿠폰(true, discount);
    }

    public static Coupon 부활_불가능_쿠폰(final CouponDiscount discount) {
        return 쿠폰(false, discount);
    }
}
