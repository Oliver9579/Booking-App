package com.example.booking.flight.DTOs;

import com.example.booking.review.DTOs.ReviewResponseDTO;
import com.example.booking.seat.models.Seat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class FlightDTO {

  private int id;
  private String airline;
  private String origin;
  private String destination;
  private Date departureDate;
  private int duration;
  private String flightNumber;
  private String flightType;
  private String img;
  private List<Seat> seats;
  private List<ReviewResponseDTO> reviews;

}
