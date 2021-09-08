package com.inflearn.member.domain;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.util.StringUtils;

public class Password {
  // 최소 숫자 1자리 이상, 영어 소문자 1자리 이상, 영어 대문자 1자리 이상, 특수문자 1자리 이상, 공백문자 포함하지 않고, 8자리 이상
  private static final Pattern pattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%!^&+=])(?=\\S+$).{8,}$");
  private final String password;

  Password(String password) {
    verify(password);
    this.password = password;
  }

  private void verify(String password) {
    if (!StringUtils.hasText(password)) {
      throw new IllegalArgumentException("비밀번호는 필수값입니다.");
    }
    Matcher matcher = pattern.matcher(password);
    if (!matcher.matches()) {
      throw new IllegalArgumentException("비밀번호 형식을 확인해주세요.");
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
    Password password1 = (Password) o;
    return Objects.equals(password, password1.password);
  }

  @Override
  public int hashCode() {
    return Objects.hash(password);
  }
}
