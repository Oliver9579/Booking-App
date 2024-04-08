package com.example.booking.review.controller;

import com.example.booking.car.models.Car;
import com.example.booking.car.services.CarService;
import com.example.booking.flight.models.Flight;
import com.example.booking.flight.services.FlightService;
import com.example.booking.hotel.models.Hotel;
import com.example.booking.hotel.services.HotelService;
import com.example.booking.review.DTOs.ReviewRequestDTO;
import com.example.booking.review.DTOs.ReviewResponseDTO;
import com.example.booking.review.models.Review;
import com.example.booking.review.services.ReviewService;
import com.example.booking.user.models.User;
import com.example.booking.user.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/reviews")
public class ReviewController {

  private ReviewService reviewService;
  private UserService userService;
  private CarService carService;
  private HotelService hotelService;
  private FlightService flightService;

  @PostMapping("/car/{carId}")
  public ResponseEntity<ReviewResponseDTO> saveCarReview(@PathVariable Integer carId,
                                                         UsernamePasswordAuthenticationToken auth,
                                                         @RequestBody ReviewRequestDTO review) {
    int userId = ((User) auth.getPrincipal()).getId();
    User user = userService.getById(userId);
    Car car = carService.getCarById(carId);
    return ResponseEntity.ok().body(reviewService.convertToResponse(
            reviewService.save(new Review(review.getComment(), user, car))));
  }

  @PostMapping("/hotel/{hotelId}")
  public ResponseEntity<ReviewResponseDTO> saveHotelReview(@PathVariable Integer hotelId,
                                                           UsernamePasswordAuthenticationToken auth,
                                                           @RequestBody ReviewRequestDTO review) {
    int userId = ((User) auth.getPrincipal()).getId();
    User user = userService.getById(userId);
    Hotel hotel = hotelService.getHotelById(hotelId);
    return ResponseEntity.ok().body(reviewService.convertToResponse(
            reviewService.save(new Review(review.getComment(), user, hotel))));
  }

  @PostMapping("/flight/{flightId}")
  public ResponseEntity<ReviewResponseDTO> saveFlightReview(@PathVariable Integer flightId,
                                                            UsernamePasswordAuthenticationToken auth,
                                                            @RequestBody ReviewRequestDTO review) {
    int userId = ((User) auth.getPrincipal()).getId();
    User user = userService.getById(userId);
    Flight flight = flightService.getFlightById(flightId);
    return ResponseEntity.ok().body(reviewService.convertToResponse(
            reviewService.save(new Review(review.getComment(), user, flight))));
  }

  @GetMapping("/car/{carId}")
  public ResponseEntity<List<ReviewResponseDTO>> getCarAllReviews(@PathVariable Integer carId) {
    Car car = carService.getCarById(carId);
    return ResponseEntity.ok().body(reviewService.getCarReviews(car));
  }

  @GetMapping("/hotel/{hotelId}")
  public ResponseEntity<List<ReviewResponseDTO>> getHotelAllReviews(@PathVariable Integer hotelId) {
    Hotel hotel = hotelService.getHotelById(hotelId);
    return ResponseEntity.ok().body(reviewService.getHotelReviews(hotel));
  }

  @GetMapping("/flight/{flightId}")
  public ResponseEntity<List<ReviewResponseDTO>> getFlightAllReviews(@PathVariable Integer flightId) {
    Flight flight = flightService.getFlightById(flightId);
    return ResponseEntity.ok().body(reviewService.getFlightReviews(flight));
  }

}