package com.inflearn.lecture.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LectureTest {
  /*
    - 지식공유자는 강의 등록을 신청할 수 있다.
      - 강의 제목, 카테고리, 난이도, 교육과정, 금액이 필요하다.
      - 금액은 0원 이상이다.
      - 제목은 비어있지 않아야한다.
    - 관리자는 강의를 승인할 수 있다.
    - 강의를 수정할 수 있다.
      - 해당 강의의 지식공유자, 관리자만 수정할 수 있다.
      - 금액은 0원 이상이다.
    - 강의 목록을 조회할 수 있다.
    - 강의 상세 내용을 조회할 수 있다.
    - 해당 강의의 수강생과 지식공유자는 강의 구성원 이다.
  */

  @DisplayName("강의를 생성할 수 있다.")
  @Test
  void create() {
    // 강의 식별자, 제목, 가격(값객체), 카테고리(이넘^^), 난이도(이넘^^),
    // Section(X) -> Chapter(이건 테이블 따로 빼야함), 태그(String으로? #자바 #스프링), 소개, 지식공유자
    Lecture lecture = new Lecture();

  }
}
