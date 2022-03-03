package com.inflearn.member.controller;

import com.inflearn.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/v1/members")
    public ResponseEntity<MemberCreateResponse> create(@RequestBody MemberCreateRequest memberCreateRequest) {
        MemberCreateResponse memberCreateResponse = memberService.create(memberCreateRequest);
        return ResponseEntity.created(URI.create("/v1/members" + memberCreateResponse.getId()))
                             .body(memberCreateResponse);
    }

    @GetMapping("v1/members{id}")
    public ResponseEntity<MemberCreateResponse> read(@PathVariable Long id) {
        MemberCreateResponse memberCreateResponse = memberService.read(id);
        return ResponseEntity.ok().body(memberCreateResponse);
    }
}
