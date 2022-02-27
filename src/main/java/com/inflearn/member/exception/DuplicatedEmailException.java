package com.inflearn.member.exception;

public class DuplicatedEmailException extends RuntimeException {

  private static final String ERROR_MESSAGE = "중복된 이메일로 회원가입 할 수 없습니다.";

  public DuplicatedEmailException() {
    super(ERROR_MESSAGE);
  }

  @Override
  public String getMessage() {
    return ERROR_MESSAGE;
  }
}
