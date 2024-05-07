package com.example.booking.booking.controllers;

import com.example.booking.booking.DTOs.AllBookingsResponseDTO;
import com.example.booking.booking.DTOs.bookingCar.BookingCarRequestDTO;
import com.example.booking.booking.DTOs.bookingCar.BookingCarResponseDTO;
import com.example.booking.booking.DTOs.bookingFlight.BookingOneWayFlightRequestDTO;
import com.example.booking.booking.DTOs.bookingFlight.BookingOneWayFlightResponseDTO;
import com.example.booking.booking.DTOs.bookingFlight.BookingRoundTripFlightRequestDTO;
import com.example.booking.booking.DTOs.bookingFlight.BookingRoundTripFlightResponseDTO;
import com.example.booking.booking.DTOs.bookingHotel.BookingHotelRequestDTO;
import com.example.booking.booking.DTOs.bookingHotel.BookingHotelResponseDTO;
import com.example.booking.booking.services.BookingService;
import com.example.booking.user.models.User;
import com.example.booking.user.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/api/bookings")
public class BookingController {

  private BookingService bookingService;
  private UserService userService;

  @PostMapping("/flights/oneWay")
  public ResponseEntity<BookingOneWayFlightResponseDTO> createOneWayNewFlightBooking(
          UsernamePasswordAuthenticationToken auth, @Valid @RequestBody BookingOneWayFlightRequestDTO bookingFlight) {
    int userId = ((User) auth.getPrincipal()).getId();
    User user = userService.getById(userId);
    return ResponseEntity.ok().body(bookingService.createOneWayFlightBooking(user, bookingFlight));

  }

  @PostMapping("/flights/roundTrip")
  public ResponseEntity<BookingRoundTripFlightResponseDTO> createRoundTripNewFlightBooking(
          UsernamePasswordAuthenticationToken auth, @Valid @RequestBody BookingRoundTripFlightRequestDTO bookingFlight) {
    int userId = ((User) auth.getPrincipal()).getId();
    User user = userService.getById(userId);
    return ResponseEntity.ok().body(bookingService.createRoundTripFlightBooking(user, bookingFlight));

  }

  @PostMapping("/hotels")
  public ResponseEntity<BookingHotelResponseDTO> createHotelBooking(
          UsernamePasswordAuthenticationToken auth, @Valid @RequestBody BookingHotelRequestDTO bookingHotel) {
    int userId = ((User) auth.getPrincipal()).getId();
    User user = userService.getById(userId);
    return ResponseEntity.ok().body(bookingService.createHotelBooking(user, bookingHotel));
  }

  @PostMapping("/cars")
  public ResponseEntity<BookingCarResponseDTO> createCarBooking(
          UsernamePasswordAuthenticationToken auth, @Valid @RequestBody BookingCarRequestDTO bookingCar) {
    int userId = ((User) auth.getPrincipal()).getId();
    User user = userService.getById(userId);
    return ResponseEntity.ok().body(bookingService.createCarBooking(user, bookingCar));
  }

  @GetMapping()
  public ResponseEntity<AllBookingsResponseDTO> getBookings(UsernamePasswordAuthenticationToken auth) {
    int userId = ((User) auth.getPrincipal()).getId();
    User user = userService.getById(userId);
    return ResponseEntity.ok().body(bookingService.getBookings(user));
  }

}
