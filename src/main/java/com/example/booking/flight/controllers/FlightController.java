package com.example.booking.flight.controllers;

import com.example.booking.flight.DTOs.*;
import com.example.booking.flight.services.FlightService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    return ResponseEntity.ok().body(flightService.convertToFlightDTO(flightService.getFlightById(id)));
  }

  @GetMapping("/oneWay")
  public ResponseEntity<FlightListDTO> getFlightsBetweenToDirectionJustOneWay(@RequestParam String origin,
                                                                              @RequestParam String destination,
                                                                              @RequestParam String departureDate) {
    FlightOneWayRequestDTO flightOneWay = FlightOneWayRequestDTO.convertOneWayDTO(origin, destination, departureDate);
    return ResponseEntity.ok().body(flightService.getFlightsJustOneWay(flightOneWay));
  }

  @GetMapping("/return")
  public ResponseEntity<FlightRoundTripList> getFlightsBetweenToDirectionJustOneWay(@RequestParam String origin,
                                                                                    @RequestParam String destination,
                                                                                    @RequestParam String departureDate,
                                                                                    @RequestParam String returnDate) {
    FlightRoundTripRequestDTO flightRoundTripRequest = FlightRoundTripRequestDTO.convertReturnDTO(origin, destination,
            departureDate, returnDate);
    return ResponseEntity.ok().body(flightService.getFlightsRoundTrip(flightRoundTripRequest));
  }

}
