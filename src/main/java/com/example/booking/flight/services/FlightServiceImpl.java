package com.example.booking.flight.services;

import com.example.booking.exceptions.IdNotFoundException;
import com.example.booking.exceptions.NoFlightException;
import com.example.booking.flight.DTOs.FlightDTO;
import com.example.booking.flight.DTOs.FlightListDTO;
import com.example.booking.flight.DTOs.FlightOneWayDTO;
import com.example.booking.flight.models.Flight;
import com.example.booking.flight.reporitories.FlightRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FlightServiceImpl implements FlightService {

  private FlightRepository flightRepository;

  @Override
  public FlightListDTO getAllFlights() {
    return new FlightListDTO(flightRepository.findAll()
            .stream()
            .map(this::convertToFlightDTO)
            .collect(Collectors.toList()));
  }

  @Override
  public FlightDTO getFlightById(Integer id) {
    return convertToFlightDTO(flightRepository.findById(id).orElseThrow(IdNotFoundException::new));
  }

  @Override
  public FlightDTO convertToFlightDTO(Flight flight) {
    return new FlightDTO(flight.getId(), flight.getAirline(), flight.getOrigin(), flight.getDestination(),
            flight.getDepartureDate(), flight.getDuration(), flight.getSeats());
  }

  @Override
  public FlightListDTO getFlightsJustOneWay(FlightOneWayDTO flightOneWay) {
    List<Flight> flights = flightRepository.findAllByOriginDestinationAndDepartureTime(flightOneWay);
    if (flights.isEmpty()) {
      throw new NoFlightException();
    } else {

      return new FlightListDTO(flights
              .stream()
              .map(this::convertToFlightDTO)
              .collect(Collectors.toList()));
    }
  }
}
