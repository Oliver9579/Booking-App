package com.example.booking.review.services;

import com.example.booking.car.models.Car;
import com.example.booking.car.services.CarService;
import com.example.booking.exceptions.IdNotFoundException;
import com.example.booking.flight.models.Flight;
import com.example.booking.flight.services.FlightService;
import com.example.booking.hotel.models.Hotel;
import com.example.booking.hotel.services.HotelService;
import com.example.booking.review.DTOs.ReviewResponseDTO;
import com.example.booking.review.models.Review;
import com.example.booking.review.repositories.ReviewRepository;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ReviewServiceImpl implements ReviewService {

  private ReviewRepository reviewRepository;

  @Override
  public Review save(Review review) {
    return reviewRepository.save(review);
  }

  @Override
  public Review getReviewById(Integer id) {
    return reviewRepository.findById(id).orElseThrow(IdNotFoundException::new);
  }

  @Override
  public List<ReviewResponseDTO> getCarReviews(Car car) {
    return car.getReviews().stream()
            .map(this::convertToResponse).collect(Collectors.toList());
  }

  @Override
  public List<ReviewResponseDTO> getHotelReviews(Hotel hotel) {
    return hotel.getReviews().stream()
            .map(this::convertToResponse).collect(Collectors.toList());
  }

  @Override
  public List<ReviewResponseDTO> getFlightReviews(Flight flight) {
    return flight.getReviews().stream()
            .map(this::convertToResponse).collect(Collectors.toList());
  }

  @Override
  public ReviewResponseDTO convertToResponse(Review review) {
    return new ReviewResponseDTO(review.getUser().getUsername(), review.getComment(), review.getReviewDate());
  }

}
