package com.inflearn.member.controller;

import com.inflearn.member.domain.Member;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class MemberCreateRequest {
    private final String email;
    private final String password;

    public Member toMember() {
        return new Member(email, password);
    }
}
