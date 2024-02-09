package com.example.booking.flight.controllers;

import com.example.booking.flight.models.Flight;
import com.example.booking.flight.services.FlightService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/booking/flights")
@AllArgsConstructor
public class FlightController {

  private FlightService flightService;

  @DeleteMapping
  private ResponseEntity<List<Flight>> getFlights() {
    return ResponseEntity.ok().body(flightService.getAllFlights());
  }

}
