package com.inflearn.lecture.domain;

import com.inflearn.member.domain.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Builder
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Lecture {
  // 강의 식별자, 제목, 가격, 카테고리, 난이도, Section -> Chapter, 태그, 소개, 지식공유자,
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String title;

  @OneToOne
  private Member instructor;

  @OneToOne
  private Category category;

  @Embedded
  private Price price;

  @Enumerated(EnumType.STRING)
  private Level level;

  private String introduce;
  // Tag, Review, Member(지식공유자)는 ID를 참조하는 형태로 설계 lifecycle이 다르다

  @Enumerated(EnumType.STRING)
  private Approval approved;

  // TODO: 2021/09/16 mappedBy, joinColumn 설정해야함
  @OneToMany(cascade = CascadeType.PERSIST)
  private List<Chapter> chapters = new ArrayList<>();

  @ManyToMany
  private List<Tag> tags = new ArrayList<>();

  public void approve() {
    if (this.approved == Approval.APPROVED) {
      throw new IllegalStateException("이미 승인되어있는 강의입니다.");
    }
    this.approved = Approval.APPROVED;
  }

  private Lecture(Long id, String title, Member instructor, Category category, Price price, Level level, String introduce, Approval approved, List<Chapter> chapters, List<Tag> tags) {
    this.id = id;
    this.title = title;
    this.instructor = instructor;
    this.category = category;
    this.price = price;
    this.level = level;
    this.introduce = introduce;
    this.approved = approved;
    this.chapters = chapters;
    this.tags = tags;
  }

  public void addChapter(Chapter chapter) {
    this.chapters.add(chapter);
  }

  public void removeChapter(Chapter chapter) {
    this.chapters.stream()
            .filter(c -> c.isActive()) // 조회할때 걸어줘야하는
            .filter(c -> Objects.equals(chapter.getId(), c.getId()))
            .findAny()
            .orElseThrow(IllegalArgumentException::new)
            .remove();
  }

  public void update(final Lecture lecture) {
    this.title = lecture.title;
    this.category = lecture.category;
    this.price = lecture.price;
    this.level = lecture.level;
    this.introduce = lecture.introduce;
  }
}