package com.inflearn.domain;

import static org.assertj.core.api.Assertions.*;

import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertAll;


public class MemberTest {

  private static final String email = "hi@email.com";
  private static final String password = "password";
  private Member member;

  /*
   * - 회원으로 등록할 수 있다.
   *     - 회원으로 등록할 때는 이메일과 비밀번호가 필요하다.
   *     - 이메일이 없으면 회원으로 등록할 수 없다.
   *     - 이메일은 인증이 가능한 이메일이어야 한다. (등록시에는 검증하지 않으나, 결제 등의 행위 시에 검증을 하게됨!!)
   *     - 이미 등록 된 이메일로는 회원가입할 수 없다.
   * - 회원은 지식공유자로 등록할 수 있다.
   * - 회원 프로필을 변경할 수 있다. PATCH
   *     - 비밀번호를 변경할 수 있다.
   * - 회원 프로필을 조회할 수 있다.
   */

  @DisplayName("회원으로 등록할 수 있다.")
  @Test
  void create() {
    // when
    member = new Member(email, password);

    // then
    assertAll(
        () -> assertThat(member).isEqualTo(new Member(email, password)), // equals와 hashcode로 동등성을 확인하는 방법
        () -> assertThat(member.isInstructor()).isFalse(),
        () -> assertThat(member.isStudent()).isFalse(),
        () -> assertThat(member.isGuest()).isTrue()
    );
  }

  @DisplayName("회원 등록시 이메일이 없으면 IllegalArgumentException을 던진다.")
  @NullAndEmptySource
  @ParameterizedTest
  void create(String email) {
    // when
    ThrowableAssert.ThrowingCallable throwingCallable = () -> new Member(email, password);

    // then
    assertThatExceptionOfType(IllegalArgumentException.class)
        .isThrownBy(throwingCallable);
  }

  @DisplayName("이미 등록된 이메일로는 회원가입할 수 없다.")
  @Test
  void create_() {
    // 고민할 요소 : 외부에서 Validator를 주입받아서 사용하는 방법의 장점
  }

  @DisplayName("회원은 지식공유자로 등록할 수 있다.")
  @Test
  void register_instructor() {
    member = new Member(email, password, MemberRole.STUDENT);

    Member expected = member.registerInstructor();

    assertThat(expected.isInstructor()).isTrue();
  }

  @DisplayName("회원이 아니면 지식공유자로 등록할 수 있다.")
  @EnumSource(value = MemberRole.class, names = {"GUEST", "INSTRUCTOR"})
  @ParameterizedTest
  void register_instructor(MemberRole role) {
    member = new Member(email, password, role);

    ThrowableAssert.ThrowingCallable throwingCallable = () -> member.registerInstructor();

    assertThatExceptionOfType(IllegalStateException.class)
        .isThrownBy(throwingCallable);
  }

  @DisplayName("회원의 프로필을 변경할 수 있다")
  @Test
  void change_profile() {
    // given
    member = new Member(email, password, MemberRole.STUDENT);
    String password = "비밀번호";
    MemberProfile profile = new MemberProfile(password);

    // when
    Member expected = member.updateProfile(profile);

    // then
    assertThat(expected).isEqualTo(new Member(member, profile));
  }
}
