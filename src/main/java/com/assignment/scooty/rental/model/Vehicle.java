package com.assignment.scooty.rental.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Document
public class Vehicle {

  private Model model;
  private Boolean availability;
  private User reservedBy;
  private LocalDate reservedUntil;
}
