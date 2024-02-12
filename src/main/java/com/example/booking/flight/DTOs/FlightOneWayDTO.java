package com.example.booking.flight.DTOs;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class FlightOneWayDTO {

  @NotBlank
  private String origin;
  @NotBlank
  private String destination;
  @NotBlank
  @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Date must be in yyyy-mm-dd format")
  private String departureDate;

}
