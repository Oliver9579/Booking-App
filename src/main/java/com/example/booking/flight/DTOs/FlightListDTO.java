package com.example.booking.flight.DTOs;

import lombok.Data;

import java.util.List;

@Data
public class FlightListDTO {

  List<FlightDTO> flights;

  public FlightListDTO(List<FlightDTO> flights) {
    this.flights = flights;
  }

}
