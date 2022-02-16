package com.inflearn.member.domain.point.domain;

import com.inflearn.common.domain.BaseEntity;
import com.inflearn.member.domain.Member;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Point extends BaseEntity {

  // Member : Point = 1 : N
  private static final int MIN_VALUE = 0;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private int value;

  private String description;

  @ManyToOne(fetch = FetchType.LAZY)
  private Member member;

  private LocalDateTime expiredTime;

  public Point(int value, String description, Member member) {
    verify(value);
    this.value = value;
    this.description = description;
    this.member = member;
    this.expiredTime = LocalDateTime.now().plus(1, ChronoUnit.YEARS);
  }

  private void verify(int value) {
    if (this.value < MIN_VALUE) {
      throw new IllegalArgumentException("포인트는 0보다 작을 수 없습니다.");
    }
  }

  public int use(int charged) {
    // 마지막 검증 용이고
    if (isExpired()) {
      throw new IllegalStateException("만료된 포인트는 사용할 수 없습니다.");
    }

    if (charged < MIN_VALUE) {
      throw new IllegalArgumentException("사용하려고하는 포인트는 0보다 작을 수 없습니다.");
    }

    // TODO: 로직 생각해보기
    // 1. 금액 > 포인트인 경우
    // 2. 금액 <= 포인트인 경우
    // 3. 반환값을 무엇으로할지 사용된 포인트만큼 해야하는 것인지에 대한 고민 필요
    return 0;
  }

  private boolean isExpired() {
    return this.expiredTime.isBefore(LocalDateTime.now());
  }
}
