package com.inflearn.domain;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.EqualsAndHashCode;
import org.springframework.util.StringUtils;

@EqualsAndHashCode
public final class Email {
  // 참고: 이펙티브 자바 6장
  private static final Pattern pattern = Pattern.compile("^(.+)@(.+)$");

  private final String email;

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


}
