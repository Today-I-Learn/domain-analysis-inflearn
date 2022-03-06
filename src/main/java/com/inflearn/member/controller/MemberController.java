package com.inflearn.member.controller;

import com.inflearn.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/v1/members/{id}")
    public ResponseEntity<MemberCreateResponse> read(@PathVariable Long id) {
        MemberCreateResponse memberCreateResponse = memberService.read(id);
        return ResponseEntity.ok().body(memberCreateResponse);
    }

    @PutMapping("/v1/members/{id}")
    public ResponseEntity<MemberUpdateResonse> update(@PathVariable Long id, @RequestBody MemberUpdateRequest request) {
        MemberUpdateResonse response = memberService.update(id, request);
        return ResponseEntity.ok().body(response);
    }
}
