package com.inflearn.member.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.inflearn.member.controller.MemberController;
import com.inflearn.member.controller.MemberCreateRequest;
import com.inflearn.member.controller.MemberCreateResponse;
import com.inflearn.member.domain.Email;
import com.inflearn.member.domain.MemberRole;
import com.inflearn.member.domain.Password;
import com.inflearn.member.exception.DuplicatedEmailException;
import com.inflearn.member.exception.MemberNotFoundException;
import com.inflearn.member.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
        MemberCreateRequest memberCreateRequest = new MemberCreateRequest(email.getValue(),
                password.getValue());
        MemberCreateResponse memberCreateResponse = new MemberCreateResponse(1L, email, password,
                MemberRole.GUEST);

        given(memberService.create(any())).willReturn(memberCreateResponse);

        // when
        ResultActions perform = mockMvc.perform(post("/v1/members")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(memberCreateRequest)));

        //then
        perform
                .andExpect(status().isCreated())
                .andExpect(jsonPath("memberRole").value(MemberRole.GUEST.name()))
                .andExpect(jsonPath("email.value").value(email.getValue()))
                .andExpect(jsonPath("password.value").value(password.getValue()));
    }

    @Test
    void 이미_등록된_이메일로는_회원가입할_수_없다() throws Exception {

        // given
        MemberCreateRequest memberCreateRequest = new MemberCreateRequest(email.getValue(),
                password.getValue());

        given(memberService.create(any())).willThrow(DuplicatedEmailException.class);

        // when
        ResultActions perform = mockMvc.perform(post("/v1/members")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(memberCreateRequest)));

        // then
        perform
                .andExpect(status().isConflict())
                .andExpect(jsonPath("message").value("중복된 이메일로 회원가입 할 수 없습니다."))
                .andExpect(jsonPath("statusCode").value(HttpStatus.CONFLICT.value()));
    }

    @Test
    void 멤버조회는_id가_존재하지_않는다면_404를_리턴한다() throws Exception {
        // given
        Long id = 1000L;
        given(memberService.read(id)).willThrow(new MemberNotFoundException("존재 하지 않는 ID : " + id));

        // 실행
        ResultActions perform = mockMvc.perform(get("/v1/members/" + 1000));

        // 검증
        perform.andExpect(status().isNotFound());
    }

    @Test
    void 멤버조회는_id가_존재한다면_저장된_id와_200을_리턴한다() throws Exception {
        // 준비
        Long id = 1L;
        given(memberService.read(id)).willReturn(new MemberCreateResponse(id, email, password, MemberRole.GUEST));

        // 실행
        ResultActions perform = mockMvc.perform(get("/v1/members/" + id));

        // 검증
        perform
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(id))
                .andExpect(jsonPath("email.value").value(email.getValue()))
                .andExpect(jsonPath("password.value").value(password.getValue()))
                .andExpect(jsonPath("memberRole").value(MemberRole.GUEST.toString()));
    }

    @Test
    void 유저_프로필_변경() throws Exception {
        // given
        Long id = 1L;
        MemberUpdateRequest memberUpdateRequest = new MemberUpdateRequest("1q2w3e4r#B");

        // when
        ResultActions perform = mockMvc.perform(put(String.format("/v1/members/{}", id))
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(memberUpdateRequest)));

        //then
        perform
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(id))
                .andExpect(jsonPath("email.value").value(email.getValue()))
                .andExpect(jsonPath("password.value").value(memberUpdateRequest.getPassword()))
                .andExpect(jsonPath("memberRole").value(MemberRole.GUEST.toString()));
    }
}
