package com.inflearn.member.domain;

import lombok.Getter;

@Getter
public class MemberProfile {
  private final Password password;

  public MemberProfile(String password) {
    this.password = new Password(password);
  }


}
