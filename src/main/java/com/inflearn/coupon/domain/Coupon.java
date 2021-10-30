package com.inflearn.coupon.domain;

import lombok.Builder;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Embedded
    private CouponDiscount couponDiscount;

    // TODO: 2021/10/30 LocalDate -> Period 
    private LocalDate period;

    private CouponStatus status = CouponStatus.DRAFT;

    private boolean revivable = false; // 부활 가능한 쿠폰인지 여부

    @OneToMany
    private List<CouponLecture> couponLectureList = new ArrayList<>();

    protected Coupon() {

    }

    public Coupon(String name, CouponDiscount couponDiscount, LocalDate period) {
        this.name = name;
        this.couponDiscount = couponDiscount;
        this.period = period;
    }

    public Coupon(String name, CouponDiscount couponDiscount, LocalDate period, boolean revivable) {
        this.name = name;
        this.couponDiscount = couponDiscount;
        this.period = period;
        this.revivable = revivable;
    }

    private Coupon(Long id, String name, CouponDiscount couponDiscount, LocalDate period, boolean revivable) {
        this.id = id;
        this.name = name;
        this.couponDiscount = couponDiscount;
        this.period = period;
        this.revivable = revivable;
    }

    public String getName() {
        return name;
    }

    public CouponDiscount getCouponDiscount() {
        return couponDiscount;
    }

    public LocalDate getPeriod() {
        return period;
    }

    public boolean isRevivable() {
        return revivable;
    }

    public BigDecimal discount(BigDecimal price) {
        return couponDiscount.discount(price);
    }

    public void activate(final List<CouponLecture> couponLectureList) {
        this.couponLectureList = couponLectureList;
        this.status = CouponStatus.ACTIVATED;
    }
}
