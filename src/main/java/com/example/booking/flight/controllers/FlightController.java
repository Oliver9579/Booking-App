package com.example.booking.flight.controllers;

import com.example.booking.flight.DTOs.*;
import com.example.booking.flight.services.FlightService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/flights")
@AllArgsConstructor
public class FlightController {

  private FlightService flightService;

  @GetMapping
  public ResponseEntity<FlightListDTO> getFlights() {
    return ResponseEntity.ok().body(flightService.getAllFlights());
  }

  @GetMapping("/{id}")
  public ResponseEntity<FlightDTO> getFlightById(@PathVariable Integer id) {
    return ResponseEntity.ok().body(flightService.getFlightById(id));
  }

  @GetMapping("/oneWay")
  public ResponseEntity<FlightListDTO> getFlightsBetweenToDirectionJustOneWay(@Valid @RequestBody FlightOneWayRequestDTO flightOneWay) {
    return ResponseEntity.ok().body(flightService.getFlightsJustOneWay(flightOneWay));
  }

  @GetMapping("/return")
  public ResponseEntity<FlightRoundTripList> getFlightsBetweenToDirectionJustOneWay(@Valid @RequestBody FlightRoundTripRequestDTO flightRoundTripRequest) {
    return ResponseEntity.ok().body(flightService.getFlightsRoundTrip(flightRoundTripRequest));
  }

}
