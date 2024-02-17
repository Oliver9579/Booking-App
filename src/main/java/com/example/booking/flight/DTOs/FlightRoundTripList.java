package com.example.booking.flight.DTOs;

import lombok.Data;

import java.util.List;

@Data
public class FlightRoundTripList {

  List<FlightRoundTripResponseDTO> flights;

  public FlightRoundTripList(List<FlightRoundTripResponseDTO> flights) {
    this.flights = flights;
  }

}
