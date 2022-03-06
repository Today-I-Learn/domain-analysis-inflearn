package com.inflearn.member.service;

import com.inflearn.member.controller.MemberCreateRequest;
import com.inflearn.member.controller.MemberCreateResponse;
import com.inflearn.member.domain.*;
import com.inflearn.member.exception.DuplicatedEmailException;
import java.util.Optional;
import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.ThrowableAssert.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class MemberServiceTest {

    private static final Long id = 1L;
    private static final Email email = Email.of("test@test.com");
    private static final Password password = Password.of("1q2w3e4r#A");
    private static final MemberRole role = MemberRole.GUEST;

    private final MemberRepository memberRepository = mock(MemberRepository.class);
    private final MemberService memberService = new MemberService(memberRepository);

    @Test
    @DisplayName("신규 회원가입을 하면 게스트로 등록이 된다.")
    void registerMember() {
        MemberCreateRequest memberCreateRequest = new MemberCreateRequest(email.getValue(),
                                                                          password.getValue());
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

    @Test
    void 이미_등록된_이메일로는_회원가입할_수_없다() {
        MemberCreateRequest memberCreateRequest = new MemberCreateRequest(email.getValue(),
                                                                          password.getValue());
        Member member = new Member(1L, email, password, MemberRole.GUEST);

        given(memberRepository.existsByEmail(email)).willReturn(true);

        ThrowingCallable callable = () -> memberService.create(memberCreateRequest);

        assertThatExceptionOfType(DuplicatedEmailException.class)
            .isThrownBy(callable)
            .withMessageMatching("중복된 이메일로 회원가입 할 수 없습니다.");
    }

    @Test
    void 멤버_조회_성공() throws Exception {
        //given
        given(memberRepository.findById(id)).willReturn(Optional.of(new Member(id, email, password, role)));
        //when
        MemberCreateResponse response = memberService.read(id);
        //then
        assertAll(
            () -> assertThat(response.getId()).isEqualTo(id),
            () -> assertThat(response.getEmail()).isEqualTo(email),
            () -> assertThat(response.getPassword()).isEqualTo(password),
            () -> assertThat(response.getMemberRole()).isEqualTo(role)
        );
    }
}
