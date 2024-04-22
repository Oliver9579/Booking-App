package com.example.booking.flight.services;

import com.example.booking.flight.DTOs.*;
import com.example.booking.flight.models.Flight;
import com.example.booking.seat.models.Seat;

import java.util.List;

public interface FlightService {

  FlightListDTO getAllFlights();

  Flight getFlightById(Integer id);

  FlightDTO convertToFlightDTO(Flight flight, List<Seat> seats);

  FlightListDTO getFlightsJustOneWay(FlightOneWayRequestDTO flightOneWay);

  FlightListDTO getFlightsRoundTrip(FlightRoundTripRequestDTO flightRoundTrip);

}
