package com.inflearn.member.domain;

import static org.assertj.core.api.Assertions.*;

import com.inflearn.member.domain.Password;
import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

public class PasswordTest {

  private String password = "1q2w3e4r#A";
  private Password pwd;

  @DisplayName("비밀번호를 생성할 수 있다.")
  @Test
  void create() {
    // given, when
    pwd = new Password("1q2w3e4r#A");

    // then
    assertThat(pwd).isEqualTo(new Password(password));
  }

  @DisplayName("빈 문자로는 비밀번호를 만들 수 없다.")
  @NullAndEmptySource
  @ParameterizedTest
  void create_empty(String password) {
    ThrowableAssert.ThrowingCallable throwingCallable = () -> new Password(password);

    assertThatExceptionOfType(IllegalArgumentException.class)
        .isThrownBy(throwingCallable);
  }

  @DisplayName("형식에 맞지 않은 비밀번호는 IllegalArgumentException이 발생한다.")
  @ParameterizedTest
  @ValueSource(strings = {"1q2w3e4r!", "1Q2W3E4R!", "1q2W3e4R"})
  void create_fail(String password) {
    ThrowableAssert.ThrowingCallable throwingCallable = () -> new Password(password);

    assertThatExceptionOfType(IllegalArgumentException.class)
        .isThrownBy(throwingCallable);
  }
}
