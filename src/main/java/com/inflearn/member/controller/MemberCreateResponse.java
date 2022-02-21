package com.inflearn.member.controller;

import com.inflearn.member.domain.Email;
import com.inflearn.member.domain.Member;
import com.inflearn.member.domain.MemberRole;
import com.inflearn.member.domain.Password;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class MemberCreateResponse {
    private final Long id;
    private final Email email;
    private final Password password;
    private final MemberRole memberRole;

    public static MemberCreateResponse from(Member member) {
        return new MemberCreateResponse(member.getId(), member.getEmail(), member.getPassword(), member.getRole());
    }
}
