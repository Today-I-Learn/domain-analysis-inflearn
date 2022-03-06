package com.inflearn.member.controller;

import com.inflearn.member.exception.DuplicatedEmailException;
import com.inflearn.member.exception.ErrorResponse;
import com.inflearn.member.exception.MemberNotFoundException;
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

  @ExceptionHandler(MemberNotFoundException.class)
  public ResponseEntity<ErrorResponse> handleMemberNotFoundException(
          MemberNotFoundException e) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(new ErrorResponse(e.getMessage(), HttpStatus.NOT_FOUND.value()));
  }
}
