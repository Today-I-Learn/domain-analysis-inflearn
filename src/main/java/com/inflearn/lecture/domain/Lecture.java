package com.inflearn.lecture.domain;

import com.inflearn.member.domain.Member;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Lecture {
  // 강의 식별자, 제목, 가격, 카테고리, 난이도, Section -> Chapter, 태그, 소개, 지식공유자,

  private final UUID id = UUID.randomUUID();
  private String title;
  private Member instructor;
  private Price price;
  private Category category;
  private Level level;
  private String introduce;
  private List<Chapter> chapters = new ArrayList<>();
  private List<Tag> tags = new ArrayList<>();

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Lecture lecture = (Lecture) o;
    return Objects.equals(id, lecture.id) || (Objects.equals(title, lecture.title)
        && Objects.equals(instructor, lecture.instructor)
        && Objects.equals(price, lecture.price)
        && Objects.equals(category, lecture.category)
        && level == lecture.level
        && Objects.equals(introduce, lecture.introduce)
        && Objects.equals(tags, lecture.tags));
  }

  @Override
  public int hashCode() {
    return Objects.hash(title, instructor, price, category, level, introduce, tags);
  }
}
