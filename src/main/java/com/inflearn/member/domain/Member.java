package com.inflearn.member.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static com.inflearn.member.domain.MemberRole.*;

@Getter
@NoArgsConstructor
@Entity
public class Member {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Embedded
  private Email email;

  @Embedded
  private Password password;

  @Enumerated(EnumType.STRING)
  private MemberRole role;

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
}
