package com.inflearn.lecture.domain;


import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Tag {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String tag;

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
