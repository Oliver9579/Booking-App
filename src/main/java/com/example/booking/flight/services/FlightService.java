package com.example.booking.flight.services;

import com.example.booking.flight.DTOs.FlightDTO;
import com.example.booking.flight.DTOs.FlightListDTO;
import com.example.booking.flight.models.Flight;

import java.util.List;

public interface FlightService {

  FlightListDTO getAllFlights();

  FlightDTO getFlightById(Integer id);

  FlightDTO convertToFlightDTO(Flight flight);

}
