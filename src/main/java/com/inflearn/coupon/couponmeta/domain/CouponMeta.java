package com.inflearn.coupon.couponmeta.domain;

import com.inflearn.common.domain.BaseEntity;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;


// 쿠폰 자체
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class CouponMeta extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private CouponMetaType couponMetaType;

    @Enumerated(EnumType.STRING)
    private CouponExpireType couponExpireType;

    @Embedded
    private ExpireDate expireDate;

    private String name;

    private int plusDate = 0;
}
