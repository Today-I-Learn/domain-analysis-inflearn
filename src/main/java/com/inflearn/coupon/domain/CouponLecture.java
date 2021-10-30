package com.inflearn.coupon.domain;

import com.inflearn.lecture.domain.Lecture;

import javax.persistence.*;

@Entity
public class CouponLecture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Coupon coupon;

    @ManyToOne
    private Lecture lecture;

    protected CouponLecture() {
    }
}
