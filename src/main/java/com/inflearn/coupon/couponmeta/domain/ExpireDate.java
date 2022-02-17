package com.inflearn.coupon.couponmeta.domain;

import java.time.LocalDateTime;
import javax.persistence.Embeddable;

@Embeddable
public class ExpireDate {

    private LocalDateTime expireDate;

    protected ExpireDate() {}

    ExpireDate(LocalDateTime expireDate) {
        verify(expireDate);
        this.expireDate = expireDate;
    }

    private void verify(LocalDateTime expireDate) {
        if (expireDate.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("현재보다 미래시점에 만료일자를 입력해야 합니다.");
        }
    }
}
