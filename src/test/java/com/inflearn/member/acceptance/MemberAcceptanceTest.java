package com.inflearn.member.acceptance;

import com.inflearn.member.controller.MemberCreateRequest;
import com.inflearn.member.controller.MemberCreateResponse;
import com.inflearn.member.domain.Email;
import com.inflearn.member.domain.MemberRole;
import com.inflearn.member.domain.Password;
import com.inflearn.member.exception.ErrorResponse;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

/*\
    `GUEST` : 게스트 (회원 가입을 한 상태)
    `STUDENT` : 회원 (이메일 인증을 받은 상태)
    `INSTRUCTOR` : 지식 공유자 (지식 공유자로 등록된 회원)

    - [x]  게스트로 등록할 수 있다.
    - [x]  게스트로 등록할 때는 이메일과 비밀번호가 필요하다.
    - [x]  유효한 이메일이 아니면 회원가입 할 수 없다.
    - [x]  비밀번호가 없으면 회원가입 할 수 없다.
    - [X]  이미 등록된 이메일로는 회원가입할 수 없다.
    - [ ]  게스트는 이메일 인증을 받아 회원이 될 수 있다.
    - [x]  회원은 지식공유자로 등록할 수 있다. (@Daniel)
    - [ ]  이메일 인증을 받아야한다.
//    - [x]  회원 프로필을 변경할 수 있다. 날림
    - [x]  비밀번호를 변경할 수 있다. (@Zito)
    - [x]  멤버를 조회할 수 있다.(ID를 기반으로) (@Felix)
*/

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MemberAcceptanceTest {

  private static final Email email = Email.of("test@test.com");
  private static final Password password = Password.of("1q2w3e4r#A");

  @LocalServerPort
  int port;

  @BeforeEach
  void setUp() {
    RestAssured.port = port;
  }

  @Test
  @DisplayName("신규 회원가입을 하면 게스트로 등록이 된다.")
  void registerMember() {

    MemberCreateRequest memberCreateRequest = new MemberCreateRequest(email.getValue(),
        password.getPassword());

    ExtractableResponse<Response> response = given().log().all()
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .body(memberCreateRequest)
        .when().post("/v1/members")
        .then().log().all()
        .extract();

    MemberCreateResponse memberCreateResponse = response.as(MemberCreateResponse.class);

    assertAll(
        () -> assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value()),
        () -> assertThat(memberCreateResponse.getEmail()).isEqualTo(email),
        () -> assertThat(memberCreateResponse.getPassword()).isEqualTo(password),
        () -> assertThat(memberCreateResponse.getMemberRole()).isEqualTo(MemberRole.GUEST)
    );
  }

  @Test
  void 이미_등록된_이메일로는_회원가입할_수_없다() {

    MemberCreateRequest memberCreateRequest = new MemberCreateRequest(email.getValue(),
        password.getPassword());

    given().log().all()
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .body(memberCreateRequest)
        .when().post("/v1/members")
        .then().log().all()
        .extract();

    ExtractableResponse<Response> response = given().log().all()
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .body(memberCreateRequest)
        .when().post("/v1/members")
        .then().log().all()
        .extract();

    assertThat(response.statusCode()).isEqualTo(HttpStatus.CONFLICT.value());
  }
}
