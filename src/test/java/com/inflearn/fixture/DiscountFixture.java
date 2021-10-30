package com.inflearn.fixture;

import com.inflearn.lecture.discount.domain.Discount;

import java.time.LocalDateTime;

public class DiscountFixture {
    public static final Long ID = 1L;
    public static final String TITLE = "갓인프런할인";
    public static final int RATIO = 50;
    public static final LocalDateTime START_TIME = LocalDateTime.of(2021, 10, 30, 15, 14);
    public static final LocalDateTime END_TIME = LocalDateTime.of(2021, 10, 30, 22, 00);
    public static final LocalDateTime LONG_TIME_AGO = LocalDateTime.MIN;

    public static Discount 할인() {
        return 할인(RATIO, END_TIME);
    }
    public static Discount 종료된_할인() {
        return 할인(RATIO, START_TIME);
    }

    public static Discount 할인율(final int ratio) {
        return 할인(ratio, END_TIME);
    }

    private static Discount 할인(final int ratio, final LocalDateTime endTime) {
        return Discount.builder()
                .id(ID)
                .title(TITLE)
                .ratio(ratio)
                .startTime(START_TIME)
                .endTime(endTime)
                .build();
    }

}
