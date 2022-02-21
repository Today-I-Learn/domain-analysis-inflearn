package com.inflearn.member.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DataJpaTest
class MemberRepositoryTest {

    private static final Email email = Email.of("test@test.com");
    private static final Password password = Password.of("1q2w3e4r#A");

    @Autowired
    private MemberRepository memberRepository;

    @Test
    @DisplayName("멤버 저장 성공")
    void savedMemberSuccess() {
        Member savedMember = memberRepository.save(new Member(email, password, MemberRole.GUEST));

        assertAll(
                () -> assertThat(savedMember.getId()).isEqualTo(1L),
                () -> assertThat(savedMember.getEmail()).isEqualTo(email),
                () -> assertThat(savedMember.getPassword()).isEqualTo(password),
                () -> assertThat(savedMember.getRole()).isEqualTo(MemberRole.GUEST)
        );
    }
}
