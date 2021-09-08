package com.inflearn.fixture;

import com.inflearn.member.domain.Member;
import com.inflearn.member.domain.MemberRole;

public class MemberFixture {

  private static final String email = "hi@email.com";
  private static final String password = "1q2w3e4r@T";

  public static Member 게스트() {
    return new Member(email, password, MemberRole.GUEST);
  }

  public static Member 회원() {
    return new Member(email, password, MemberRole.STUDENT);
  }

  public static Member 지식공유자() {
    return new Member(email, password, MemberRole.INSTRUCTOR);
  }


}
