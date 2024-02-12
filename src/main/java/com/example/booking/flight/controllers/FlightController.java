package com.example.booking.flight.controllers;

import com.example.booking.flight.DTOs.FlightDTO;
import com.example.booking.flight.DTOs.FlightListDTO;
import com.example.booking.flight.services.FlightService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
