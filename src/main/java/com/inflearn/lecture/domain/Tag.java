package com.inflearn.lecture.domain;

import org.springframework.util.StringUtils;

import java.util.Objects;
import java.util.UUID;

public class Tag {
  private final UUID id = UUID.randomUUID();
  private final String tag;

  public Tag(String tag) {
    verify(tag);
    this.tag = tag;
  }

  private void verify(String tag) {
    if (!StringUtils.hasText(tag)) {
      throw new IllegalArgumentException("빈값 쓰지마!");
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Tag tag1 = (Tag) o;
    return Objects.equals(id, tag1.id) || Objects.equals(tag, tag1.tag);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, tag);
  }
}
