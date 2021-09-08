package com.inflearn.lecture.domain;

import static org.assertj.core.api.Assertions.*;

import java.math.BigDecimal;
import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class PriceTest {

  private Price price;

  @DisplayName("금액을 생성할 수 있다.")
  @Test
  void create() {
    price = new Price(BigDecimal.valueOf(1_000_000));

    assertThat(price).isEqualTo(new Price(BigDecimal.valueOf(1_000_000)));
  }

  @DisplayName("금액은 0원 이상이어야 한다.")
  @ParameterizedTest
  @ValueSource(strings = "-1")
  void test(BigDecimal value) {
    ThrowingCallable throwingCallable = () -> new Price(value);

    assertThatExceptionOfType(IllegalArgumentException.class)
        .isThrownBy(throwingCallable);
  }

  // TODO: 2021/09/08 할인 기능 필요
}
