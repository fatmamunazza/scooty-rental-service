package com.assignment.scooty.rental.model;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Model {
  Model1("model1"),
  Model2("model2"),
  Model3("model3");

  private String value;

  private Model(String value) {
    this.value = value;
  }

  public String getValue() {
    return this.value;
  }
}
