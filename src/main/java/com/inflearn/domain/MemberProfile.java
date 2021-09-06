package com.inflearn.domain;

import lombok.Getter;

@Getter
public class MemberProfile {

  private final String password;

  public MemberProfile(String password) {
    this.password = password;
  }
}
