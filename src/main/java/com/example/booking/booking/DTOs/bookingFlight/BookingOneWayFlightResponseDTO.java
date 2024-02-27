package com.example.booking.booking.DTOs.bookingFlight;

import com.example.booking.flight.DTOs.FlightDTO;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BookingOneWayFlightResponseDTO {

  private LocalDateTime bookingDate;
  private LocalDateTime startDate;
  private int totalPrice;
  private FlightDTO flight;

  public BookingOneWayFlightResponseDTO(LocalDateTime bookingDate, LocalDateTime startDate,
                                        int totalPrice, FlightDTO flight) {
    this.bookingDate = bookingDate;
    this.startDate = startDate;
    this.totalPrice = totalPrice;
    this.flight = flight;
  }

}
