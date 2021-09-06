package com.inflearn.domain;

import static com.inflearn.domain.MemberRole.*;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.util.StringUtils;

@EqualsAndHashCode
@Getter
public class Member {
  private final Email email;
  private final String password;
  private final MemberRole role;

  public Member(String email, String password) {
    this(email, password, GUEST);
  }

  public Member(Member member, MemberProfile profile) {
    this.email = member.email;
    this.password = profile.getPassword();
    this.role = member.role;
  }

  public Member(String email, String password, MemberRole role) {
    this(new Email(email), password, role);
  }

  public Member(Email email, String password, MemberRole role) {
    verify(password);
    this.email = email;
    this.password = password;
    this.role = role;
  }


  private void verify(String password) {
    if (!StringUtils.hasText(password)) {
      throw new IllegalArgumentException("비밀번호는 필수값입니다.");
    }
  }

  public boolean isInstructor() {
    return role == INSTRUCTOR;
  }

  public boolean isStudent() {
    return role == STUDENT;
  }

  public boolean isGuest() {
    return role == GUEST;
  }

  public Member registerInstructor() {
    if(!this.isStudent()) {
      throw new IllegalStateException("회원만 지식공유자 가능함~");
    }
    return new Member(this.email, this.password, INSTRUCTOR);
  }

  public Member updateProfile(MemberProfile profile) {
    return new Member(this, profile);
  }
}
