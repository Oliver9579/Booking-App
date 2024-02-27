package com.example.booking.flight.services;

import com.example.booking.flight.DTOs.*;
import com.example.booking.flight.models.Flight;

public interface FlightService {

  FlightListDTO getAllFlights();

  Flight getFlightById(Integer id);

  FlightDTO convertToFlightDTO(Flight flight);

  FlightListDTO getFlightsJustOneWay(FlightOneWayRequestDTO flightOneWay);

  FlightRoundTripList getFlightsRoundTrip(FlightRoundTripRequestDTO flightRoundTrip);

}
