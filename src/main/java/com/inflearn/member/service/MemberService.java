package com.inflearn.member.service;

import com.inflearn.member.controller.MemberCreateRequest;
import com.inflearn.member.controller.MemberCreateResponse;
import com.inflearn.member.controller.MemberUpdateRequest;
import com.inflearn.member.controller.MemberUpdateResonse;
import com.inflearn.member.domain.Email;
import com.inflearn.member.domain.Member;
import com.inflearn.member.domain.MemberRepository;
import com.inflearn.member.exception.DuplicatedEmailException;
import com.inflearn.member.exception.MemberNotFoundException;

import java.util.Optional;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public MemberCreateResponse create(MemberCreateRequest memberCreateRequest) {
        if (memberRepository.existsByEmail(Email.of(memberCreateRequest.getEmail()))) {
            throw new DuplicatedEmailException();
        }

        Member savedMember = memberRepository.save(memberCreateRequest.toMember());
        return MemberCreateResponse.from(savedMember);
    }


    @Transactional(readOnly = true)
    public MemberCreateResponse read(Long id) {
        Member findMember = memberRepository.findById(id)
                .orElseThrow(() -> new MemberNotFoundException("존재 하지 않는 ID : " + id));
        return MemberCreateResponse.from(findMember);
    }

    @Transactional
    public MemberUpdateResonse update(long id, MemberUpdateRequest memberUpdateRequest) {
        Member member = memberRepository.findById(id)
                .orElseThrow(RuntimeException::new);
        member.updateProfile(memberUpdateRequest.toMemberProfile());
        return MemberUpdateResonse.from(member);
    }

}
