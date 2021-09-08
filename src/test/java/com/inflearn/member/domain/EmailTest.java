package com.inflearn.member.domain;

import static org.assertj.core.api.Assertions.*;

import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

public class EmailTest {

  String userEmail = "vincentj@naver.com";
  private Email email;

  @DisplayName("이메일을 생성할 수 있다")
  @Test
  void create() {
    this.email = new Email(userEmail);

    assertThat(email).isEqualTo(new Email(userEmail));
  }

  @DisplayName("빈 문자로 이메일 성생시 IllegalArgumentException이 발생한다.")
  @NullAndEmptySource
  @ParameterizedTest
  void create(String userEmail) {
    ThrowableAssert.ThrowingCallable throwingCallable = () -> new Email(userEmail);

    assertThatExceptionOfType(IllegalArgumentException.class)
        .isThrownBy(throwingCallable);
  }

  @DisplayName("형식에 맞지 않는 이메일은 IllegalArgumentException이 발생한다.")
  @ParameterizedTest
  @ValueSource(strings = {"email123","@naver.com"})
  void create_fail(String userEmail) {
    ThrowableAssert.ThrowingCallable throwingCallable = () -> new Email(userEmail);

    assertThatExceptionOfType(IllegalArgumentException.class)
        .isThrownBy(throwingCallable);
  }
}
