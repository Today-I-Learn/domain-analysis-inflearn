package com.inflearn.lecture.domain;

import com.inflearn.member.domain.Member;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
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

  // TODO: 2021/09/16 mappedBy, joinColumn 설정해야함
  @OneToMany
  private List<Chapter> chapters = new ArrayList<>();

  @OneToMany
  private List<Tag> tags = new ArrayList<>(); // id


}
