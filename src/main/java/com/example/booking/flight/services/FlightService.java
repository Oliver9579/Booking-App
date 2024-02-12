package com.example.booking.flight.services;

import com.example.booking.flight.DTOs.FlightDTO;
import com.example.booking.flight.DTOs.FlightListDTO;
import com.example.booking.flight.DTOs.FlightOneWayDTO;
import com.example.booking.flight.models.Flight;

public interface FlightService {

  FlightListDTO getAllFlights();

  FlightDTO getFlightById(Integer id);

  FlightDTO convertToFlightDTO(Flight flight);

  FlightListDTO getFlightsJustOneWay(FlightOneWayDTO flightOneWay);

}
