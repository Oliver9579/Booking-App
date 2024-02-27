package com.example.booking.booking.controllers;

import com.example.booking.booking.DTOs.bookingFlight.BookingOneWayFlightRequestDTO;
import com.example.booking.booking.services.BookingService;
import com.example.booking.user.models.User;
import com.example.booking.user.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/api/bookings")
public class BookingController {

  private BookingService bookingService;
  private UserService userService;

  @PostMapping("/flights/oneWay")
  public ResponseEntity<?> createOneWayNewFlightBooking(UsernamePasswordAuthenticationToken auth,
                                                        @Valid @RequestBody BookingOneWayFlightRequestDTO bookingFlight) {
    int userId = ((User) auth.getPrincipal()).getId();
    User user = userService.getById(userId);
    return ResponseEntity.ok().body(bookingService.createOneWayFlightBooking(user, bookingFlight));

  }

}
