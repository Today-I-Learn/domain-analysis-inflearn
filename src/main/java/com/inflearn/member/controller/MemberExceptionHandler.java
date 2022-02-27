package com.inflearn.member.controller;

import com.inflearn.member.exception.DuplicatedEmailException;
import com.inflearn.member.exception.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MemberExceptionHandler {

  @ExceptionHandler(DuplicatedEmailException.class)
  public ResponseEntity<ErrorResponse> handleDuplicatedEmailException(
      DuplicatedEmailException e) {
    return ResponseEntity.status(HttpStatus.CONFLICT)
        .body(new ErrorResponse(e.getMessage(), HttpStatus.CONFLICT.value()));
  }
}
