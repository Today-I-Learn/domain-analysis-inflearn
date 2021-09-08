package com.inflearn.lecture.domain;

import java.math.BigDecimal;
import java.util.Objects;
import lombok.EqualsAndHashCode;

public class Price {
  private final BigDecimal value;

  public Price(int value) {
    this(BigDecimal.valueOf(value));
  }

  public Price(BigDecimal value) {
    verify(value);
    this.value = value;
  }

  private void verify(BigDecimal value) {
    if(value.compareTo(BigDecimal.ZERO) < 0) {
      throw new IllegalArgumentException("0원 이상이라고~");
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Price price = (Price) o;
    return Objects.equals(value, price.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(value);
  }
}
