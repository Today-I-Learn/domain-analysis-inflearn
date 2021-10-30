package com.inflearn.coupon.domain;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.math.BigDecimal;
import java.util.Objects;

@Embeddable
public class CouponDiscount {
    private static final int MIN_VALUE = 1;

    @Enumerated(EnumType.STRING)
    private CouponType type = CouponType.RATIO;
    private int value;

    protected CouponDiscount() {

    }

    public CouponDiscount(CouponType type, int value) {
        verifyValue(value);
        this.type = type;
        this.value = value;
    }

    private void verifyValue(int value) {
        if (value < MIN_VALUE) {
            throw new IllegalArgumentException("0이하의 값은 사용할 수 없습니다.");
        }
    }

    public BigDecimal discount(BigDecimal price) {
        return type.calculate(price, BigDecimal.valueOf(value));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CouponDiscount that = (CouponDiscount) o;
        return value == that.value && type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, value);
    }
}
