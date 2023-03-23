package com.assignment.scooty.rental.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Document
public class Outlet {
  @Id private String id;
  private OutletName outletName;
  private Integer parkingSlots;

  private List<Vehicle> vehicles;
}
