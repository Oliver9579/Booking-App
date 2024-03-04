package com.example.booking.flight.DTOs;

import com.example.booking.exceptions.FormInputIsEmpty;
import lombok.Data;

@Data
public class FlightOneWayRequestDTO {

  private String origin;
  private String destination;
  private String departureDate;

  public FlightOneWayRequestDTO(String origin, String destination, String departureDate) {
    this.origin = origin;
    this.destination = destination;
    this.departureDate = departureDate;
  }

  public static FlightOneWayRequestDTO convertOneWayDTO(String origin, String destination, String departureDate) {
    validate(origin, destination, departureDate);
    return new FlightOneWayRequestDTO(origin, destination, departureDate);
  }

  private static void validate(String origin, String destination, String departureDate) {
    if (origin.isBlank() || destination.isBlank() || departureDate.isBlank()) {
      throw new FormInputIsEmpty();
    }
  }

}
