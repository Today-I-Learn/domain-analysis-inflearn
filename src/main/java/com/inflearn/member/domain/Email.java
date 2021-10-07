package com.inflearn.member.domain;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.util.StringUtils;

import javax.persistence.Embeddable;

@Embeddable
public final class Email {
  // 참고: 이펙티브 자바 6장
  private static final Pattern pattern = Pattern.compile("^(.+)@(.+)$");

  private String email;

  protected Email() {}

  Email(String email) {
    verify(email);
    this.email = email;
  }

  private void verify(String email) {
    if(!StringUtils.hasText(email)) {
      throw new IllegalArgumentException("이메일 형식은 비울수없어용.");
    }
    Matcher matcher = pattern.matcher(email);
    if (!matcher.matches()) {
      throw new IllegalArgumentException("이메일 형식을 확인해주세요.");
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
    Email email1 = (Email) o;
    return Objects.equals(email, email1.email);
  }

  @Override
  public int hashCode() {
    return Objects.hash(email);
  }
}
