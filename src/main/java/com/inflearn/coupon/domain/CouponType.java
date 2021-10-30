package com.inflearn.coupon.domain;

import java.math.BigDecimal;
import java.util.function.BinaryOperator;

public enum CouponType {
    PRICE((price, discountValue) -> price.subtract(discountValue)),
    RATIO((price, discountValue) ->
            price.multiply(BigDecimal.ONE.subtract(discountValue.divide(BigDecimal.valueOf(100)))).stripTrailingZeros());

    private final BinaryOperator<BigDecimal> calculator;

    CouponType(BinaryOperator<BigDecimal> calculator) {
        this.calculator = calculator;
    }

    public BigDecimal calculate(BigDecimal price, BigDecimal discountValue) {
        final BigDecimal result = calculator.apply(price, discountValue);
        return result.compareTo(BigDecimal.ZERO) > 0 ? result : BigDecimal.ZERO;
    }
}
