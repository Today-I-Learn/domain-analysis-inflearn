package com.inflearn.member.domain;

import static com.inflearn.member.domain.MemberRole.*;

import java.util.Objects;
import java.util.UUID;
import lombok.Getter;

@Getter
public class Member {
  private final UUID id = UUID.randomUUID();
  private final Email email;
  private final Password password;
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
    this(new Email(email), new Password(password), role);
  }

  public Member(Email email, Password password, MemberRole role) {
    this.email = email;
    this.password = password;
    this.role = role;
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

  public Member registerStudent() {
    if(!this.isGuest()){
      throw new IllegalStateException("게스트만 사용자로 등록 가능");
    }
    return new Member(this.email, this.password, STUDENT);
  }

  public Member updateProfile(MemberProfile profile) {
    return new Member(this, profile);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Member member = (Member) o;
    return Objects.equals(id, member.id) || (Objects.equals(email, member.email)
        && Objects.equals(password, member.password)
        && role == member.role);
  }

  @Override
  public int hashCode() {
    return Objects.hash(email, password, role);
  }
}
