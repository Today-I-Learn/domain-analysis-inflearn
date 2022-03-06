package com.inflearn.member.controller;

import com.inflearn.member.domain.MemberProfile;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class MemberUpdateRequest {
    private final String password;
    
    public MemberProfile toMemberProfile() {
        return new MemberProfile(password);
    }
}
