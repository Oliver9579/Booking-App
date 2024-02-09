package com.example.booking.flight.services;

import com.example.booking.flight.models.Flight;
import com.example.booking.flight.reporitories.FlightRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FlightServiceImpl implements FlightService {

  private FlightRepository flightRepository;

  @Override
  public List<Flight> getAllFlights() {
    return flightRepository.findAll();
  }
}
