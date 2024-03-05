package com.example.booking.flight.DTOs;

import com.example.booking.exceptions.FormInputIsEmpty;
import lombok.Data;

@Data
public class FlightRoundTripRequestDTO {

  private String origin;
  private String destination;
  private String departureDate;
  private String returnDate;
  private String returnOrigin;
  private String returnDestination;

  public FlightRoundTripRequestDTO(String origin, String destination, String departureDate, String returnDate) {
    this.origin = origin;
    this.destination = destination;
    this.departureDate = departureDate;
    this.returnDate = returnDate;
    this.returnOrigin = destination;
    this.returnDestination = origin;
  }

  public static FlightRoundTripRequestDTO convertReturnDTO(String origin, String destination,
                                                        String departureDate, String returnDate) {
    validate(origin, destination, departureDate, returnDate);
    return new FlightRoundTripRequestDTO(origin, destination, departureDate, returnDate);
  }

  private static void validate(String origin, String destination, String departureDate, String returnDate) {
    if (origin.isBlank() || destination.isBlank() || departureDate.isBlank() || returnDate.isBlank()) {
      throw new FormInputIsEmpty();
    }
  }

}
