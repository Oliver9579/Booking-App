package com.example.booking.flight.DTOs;

import com.example.booking.seat.models.Seat;
import lombok.*;

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
  private List<Seat> seats;

}
