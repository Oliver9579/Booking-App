package com.example.booking.flight.services;

import com.example.booking.exceptions.IdNotFoundException;
import com.example.booking.exceptions.NoFlightFoundException;
import com.example.booking.exceptions.SameDateException;
import com.example.booking.flight.DTOs.FlightDTO;
import com.example.booking.flight.DTOs.FlightListDTO;
import com.example.booking.flight.DTOs.FlightOneWayRequestDTO;
import com.example.booking.flight.DTOs.FlightRoundTripRequestDTO;
import com.example.booking.flight.models.Flight;
import com.example.booking.flight.reporitories.FlightRepository;
import com.example.booking.review.services.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FlightServiceImpl implements FlightService {

  private FlightRepository flightRepository;
  private ReviewService reviewService;

  @Override
  public FlightListDTO getAllFlights() {
    return new FlightListDTO(flightRepository.findAll()
            .stream()
            .map(this::convertToFlightDTO)
            .collect(Collectors.toList()));
  }

  @Override
  public Flight getFlightById(Integer id) {
    return flightRepository.findById(id).orElseThrow(IdNotFoundException::new);
  }

  @Override
  public FlightDTO convertToFlightDTO(Flight flight) {
    return new FlightDTO(flight.getId(), flight.getAirline(), flight.getOrigin(), flight.getOriginAirportCode(),
            flight.getDestination(), flight.getDestinationAirportCode(), flight.getDepartureDate(),
            flight.getDuration(), flight.getFlightNumber(), flight.getFlightType(), flight.getImg(), flight.getSeats(),
            flight.getReviews().stream().map(review -> reviewService.convertToResponse(review)).collect(Collectors.toList()));
  }

  @Override
  public FlightListDTO getFlightsJustOneWay(FlightOneWayRequestDTO flightOneWay) {
    List<Flight> flights = flightRepository.findAllByOriginDestinationAndDepartureTime(flightOneWay);
    if (flights.isEmpty()) {
      throw new NoFlightFoundException();
    } else {
      return new FlightListDTO(flights
              .stream()
              .map(this::convertToFlightDTO)
              .collect(Collectors.toList()));
    }
  }

  @Override
  public FlightListDTO getFlightsRoundTrip(FlightRoundTripRequestDTO flightRoundTrip) {
    if (flightRoundTrip.getDepartureDate().equals(flightRoundTrip.getReturnDate())) throw new SameDateException();
    ArrayList<FlightDTO> flights = new ArrayList<>();
    List<List<Object>> flightsId = flightRepository.findByDestination(flightRoundTrip);
    if (flightsId.isEmpty()) throw new NoFlightFoundException();

    for (int i = 0; i < flightsId.size(); i++) {
      flights.add(convertToFlightDTO(
              flightRepository.findById((Integer) flightsId.get(i).get(0)).get()));
      flights.add(convertToFlightDTO(
              flightRepository.findById((Integer) flightsId.get(i).get(1)).get()));
    }
    return new FlightListDTO(flights);
  }

}
