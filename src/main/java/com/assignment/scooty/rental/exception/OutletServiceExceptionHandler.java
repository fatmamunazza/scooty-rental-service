package com.assignment.scooty.rental.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;

@SuppressWarnings("rawtypes")
@Slf4j
@RestControllerAdvice
public class OutletServiceExceptionHandler {

  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity handleNotFoundException(NotFoundException ex) {
    log.error("Processing request (NFE)", ex);
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .contentType(MediaType.APPLICATION_JSON)
        .body(Arrays.asList(ex.getERROR()));
  }
}
