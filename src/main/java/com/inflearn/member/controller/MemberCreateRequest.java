package com.inflearn.member.controller;

import com.inflearn.member.domain.Email;
import com.inflearn.member.domain.Password;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class MemberCreateRequest {
    private final Email email;
    private final Password password;
}
