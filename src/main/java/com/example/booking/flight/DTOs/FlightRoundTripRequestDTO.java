package com.example.booking.flight.DTOs;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class FlightRoundTripRequestDTO {

  @NotBlank
  private String origin;
  @NotBlank
  private String destination;
  @NotBlank
  @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Date must be in yyyy-mm-dd format")
  private String departureDate;
  @NotBlank
  @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Date must be in yyyy-mm-dd format")
  private String returnDate;
  @JsonIgnore
  private String returnOrigin;
  @JsonIgnore
  private String returnDestination;

  public FlightRoundTripRequestDTO(String origin, String destination, String departureDate, String returnDate) {
    this.origin = origin;
    this.destination = destination;
    this.departureDate = departureDate;
    this.returnDate = returnDate;
    this.returnOrigin = destination;
    this.returnDestination = origin;
  }
}
