package com.inflearn.wishlist.domain;

import com.inflearn.lecture.domain.Lecture;
import lombok.Getter;

@Getter
public class CartInLectureRemovedEvent {
    private final Lecture lecture;
    private final Long memberId;

    public CartInLectureRemovedEvent(Lecture lecture, Long memberId) {
        this.lecture = lecture;
        this.memberId = memberId;
    }
}
