package com.assignment.scooty.rental.model;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum OutletName {
  outlet1("outlet1"),
  outlet2("outlet2"),
  outlet3("outlet3");

  private String value;

  private OutletName(String value) {
    this.value = value;
  }

  public String getValue() {
    return this.value;
  }
}
