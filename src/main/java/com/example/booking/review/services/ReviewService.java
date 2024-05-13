package com.example.booking.review.services;

import com.example.booking.car.models.Car;
import com.example.booking.flight.models.Flight;
import com.example.booking.hotel.models.Hotel;
import com.example.booking.review.DTOs.ReviewResponseDTO;
import com.example.booking.review.models.Review;

import java.util.List;

public interface ReviewService {

  Review save(Review review);

  List<ReviewResponseDTO> getCarReviews(Car car);

  List<ReviewResponseDTO> getHotelReviews(Hotel hotel);

  List<ReviewResponseDTO> getFlightReviews(Flight flight);

  ReviewResponseDTO convertToResponse(Review review);

}
