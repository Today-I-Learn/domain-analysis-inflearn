package com.inflearn.member.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class MemberCreateRequest {
    private final String email;
    private final String password;
}
