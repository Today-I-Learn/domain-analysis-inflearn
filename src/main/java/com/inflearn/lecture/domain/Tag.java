package com.inflearn.lecture.domain;

import java.util.UUID;

public class Tag {
  private final UUID id = UUID.randomUUID();
  private final String tag;

  public Tag(String tag) {
    this.tag = tag;
  }
}
