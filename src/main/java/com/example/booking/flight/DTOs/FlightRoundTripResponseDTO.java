package com.example.booking.flight.DTOs;

import lombok.Data;

@Data
public class FlightRoundTripResponseDTO {

  private FlightDTO flightToDestination;
  private FlightDTO flightReturn;

  public FlightRoundTripResponseDTO(FlightDTO flightToDestination, FlightDTO flightReturn) {
    this.flightToDestination = flightToDestination;
    this.flightReturn = flightReturn;
  }
}
