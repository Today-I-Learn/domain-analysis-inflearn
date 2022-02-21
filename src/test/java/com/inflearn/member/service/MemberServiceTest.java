package com.inflearn.member.service;

import com.inflearn.member.controller.MemberCreateRequest;
import com.inflearn.member.controller.MemberCreateResponse;
import com.inflearn.member.domain.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class MemberServiceTest {

    private static final Long id = 1L;
    private static final Email email = Email.of("test@test.com");
    private static final Password password = Password.of("1q2w3e4r#A");

    private final MemberRepository memberRepository = mock(MemberRepository.class);
    private final MemberService memberService = new MemberService(memberRepository);

    @Test
    @DisplayName("신규 회원가입을 하면 게스트로 등록이 된다.")
    void registerMember() {
        MemberCreateRequest memberCreateRequest = new MemberCreateRequest(email.getEmail(), password.getPassword());
        Member member = new Member(1L, email, password, MemberRole.GUEST);

        given(memberRepository.save(any())).willReturn(member);

        MemberCreateResponse memberCreateResponse = memberService.create(memberCreateRequest);

        assertAll(
                () -> assertThat(memberCreateResponse.getId()).isEqualTo(id),
                () -> assertThat(memberCreateResponse.getEmail()).isEqualTo(email),
                () -> assertThat(memberCreateResponse.getPassword()).isEqualTo(password),
                () -> assertThat(memberCreateResponse.getMemberRole()).isEqualTo(MemberRole.GUEST)
        );
    }
}
