package com.inflearn.cart.domain;

import com.inflearn.lecture.domain.Lecture;
import lombok.Getter;

@Getter
public class WishListInLectureRemovedEvent {
    private final Lecture lecture;
    private final Long memberId;

    public WishListInLectureRemovedEvent(Lecture lecture, Long memberId) {
        this.lecture = lecture;
        this.memberId = memberId;
    }
}
