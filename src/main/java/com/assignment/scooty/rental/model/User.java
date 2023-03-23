package com.assignment.scooty.rental.model;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum User {
  USER1("user1"),
  USER2("user2"),
  USER3("user3");

  private String value;

  private User(String value) {
    this.value = value;
  }

  public String getValue() {
    return this.value;
  }
}
