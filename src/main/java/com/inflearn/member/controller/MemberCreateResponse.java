package com.inflearn.member.controller;

import com.inflearn.member.domain.Email;
import com.inflearn.member.domain.MemberRole;
import com.inflearn.member.domain.Password;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@RequiredArgsConstructor
public class MemberCreateResponse {
    private final Long id;
    private final Email email;
    private final Password password;
    private final MemberRole memberRole;
}
