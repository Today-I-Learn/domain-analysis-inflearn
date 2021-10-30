package com.inflearn.lecture.discount.domain;

import com.inflearn.common.domain.BaseEntity;
import lombok.Builder;
import org.springframework.util.StringUtils;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Objects;

@Builder
@Entity
public class Discount extends BaseEntity {

    private static final int MIN_RATIO = 1;
    private static final int MAX_RATIO = 100;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private int ratio;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    protected Discount() {

    }

    public Discount(String title, int ratio, LocalDateTime startTime, LocalDateTime endTime) {
        this(null, title, ratio, startTime, endTime);
    }

    private Discount(Long id, String title, int ratio, LocalDateTime startTime, LocalDateTime endTime) {
        verify(title, ratio, startTime, endTime);
        this.id = id;
        this.title = title;
        this.ratio = ratio;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    private void verify(String title, int ratio, LocalDateTime startTime, LocalDateTime endTime) {
        verifyTitle(title);
        verifyRatio(ratio);
        verifyTime(startTime, endTime);
    }

    private void verifyTitle(String title) {
        if (!StringUtils.hasText(title)) {
            throw new IllegalArgumentException("제목은 있어야혀~");
        }
    }

    private void verifyRatio(int ratio) {
        if (ratio < MIN_RATIO || ratio > MAX_RATIO) {
            throw new IllegalArgumentException("깐부자너~");
        }
    }

    private void verifyTime(LocalDateTime startTime, LocalDateTime endTime) {
        if (startTime.toLocalDate().isBefore(LocalDateTime.now().toLocalDate())) {
            throw new IllegalArgumentException("시작날짜는 오늘부터");
        }

        if(startTime.isAfter(endTime)) {
            throw new IllegalArgumentException("먼저끝나는건 말이 되고?");
        }
    }

    public String getTitle() {
        return title;
    }

    public int getRatio() {
        return ratio;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void update(final Discount discount) {
        this.title = discount.title;
        this.ratio = discount.ratio;
        this.startTime = discount.startTime;
        this.endTime = discount.endTime;
    }

    public void finish() {
        this.endTime = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Discount discount = (Discount) o;
        return Objects.equals(id, discount.id) || ratio == discount.ratio && Objects.equals(title, discount.title) && Objects.equals(startTime, discount.startTime) && Objects.equals(endTime, discount.endTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, ratio, startTime, endTime);
    }
}
