package com.inflearn.lecture.review.domain;

import javax.persistence.Embeddable;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Objects;

@Embeddable
public class Rating {
    private static final int MIN_RATE = 1;
    private static final int MAX_RATE = 5;

    @Min(value = 1)
    @Max(value = 5)
    private int rate;

    protected Rating() {

    }

    public Rating(int rate) {
        verify(rate);
        this.rate = rate;
    }

    public void verify(int rate) {
        if(rate < MIN_RATE || rate > MAX_RATE) {
            throw new IllegalArgumentException("별점은 1이상 5이하의 값만 가능합니다.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rating rating = (Rating) o;
        return rate == rating.rate;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rate);
    }
}
