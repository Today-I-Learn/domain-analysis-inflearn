package com.inflearn.member.service;

import com.inflearn.member.controller.MemberCreateRequest;
import com.inflearn.member.controller.MemberCreateResponse;
import com.inflearn.member.domain.Email;
import com.inflearn.member.domain.Member;
import com.inflearn.member.domain.MemberRepository;
import com.inflearn.member.exception.DuplicatedEmailException;
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
}
