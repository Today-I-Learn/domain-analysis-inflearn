package com.inflearn.member.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.inflearn.member.domain.Email;
import com.inflearn.member.domain.MemberRole;
import com.inflearn.member.domain.Password;
import com.inflearn.member.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MemberController.class)
public class MemberControllerTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    private static final Email email = Email.of("test@test.com");
    private static final Password password = Password.of("1q2w3e4r#A");

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MemberService memberService;

    @Test
    @DisplayName("신규 회원가입을 하면 게스트로 등록이 된다.")
    void registerMember() throws Exception {

        // given
        MemberCreateRequest memberCreateRequest = new MemberCreateRequest(email.getEmail(), password.getPassword());
        MemberCreateResponse memberCreateResponse = new MemberCreateResponse(1L, email, password, MemberRole.GUEST);

        given(memberService.create(any())).willReturn(memberCreateResponse);

        // when
        ResultActions perform = mockMvc.perform(post("/v1/members")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(memberCreateRequest)));

        //then
        perform
                .andExpect(status().isCreated())
                .andExpect(jsonPath("memberRole").value(MemberRole.GUEST.name()))
                .andExpect(jsonPath("email.email").value(email.getEmail()))
                .andExpect(jsonPath("password.password").value(password.getPassword()));
    }
}
