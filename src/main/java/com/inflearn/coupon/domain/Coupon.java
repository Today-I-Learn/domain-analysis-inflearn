package com.inflearn.coupon.domain;

import com.inflearn.common.domain.BaseEntity;
import com.inflearn.coupon.couponmeta.domain.ExpireDate;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

// 강의를 구매하려는 유저가 등록한 쿠폰
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Coupon extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean used;

    private Long memberId;

    private Long couponMetaId;

    @Embedded
    private ExpireDate expireDate;
}
