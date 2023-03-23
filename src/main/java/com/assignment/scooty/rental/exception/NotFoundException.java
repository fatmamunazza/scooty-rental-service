package com.assignment.scooty.rental.exception;

import com.assignment.scooty.rental.dto.Error;
import org.springframework.http.HttpStatus;

public class NotFoundException extends Exception {
  private final String field;
  private final String message;

  public NotFoundException(String field, String message) {
    this.field = field;
    this.message = message;
  }

  Error getERROR() {
    return new Error()
        .code(String.valueOf(HttpStatus.NOT_FOUND.value()))
        .field(field)
        .message(message);
  }
}
