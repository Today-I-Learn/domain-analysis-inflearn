package com.inflearn.member.domain;

import com.inflearn.coupon.domain.Coupon;
import com.inflearn.lecture.domain.Lecture;

import javax.persistence.*;

@Entity
public class MemberCoupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Coupon coupon;

    @ManyToOne
    private Member lecture;

    protected MemberCoupon() {
    }
}
