package com.example.booking.booking.DTOs.bookingFlight;

import com.example.booking.flight.DTOs.FlightDTO;
import lombok.Data;

import java.util.Date;

@Data
public class BookingOneWayFlightResponseDTO {

  private Date bookingDate;
  private Date startDate;
  private int totalPrice;
  private FlightDTO flight;

  public BookingOneWayFlightResponseDTO(Date bookingDate, Date startDate,
                                        int totalPrice, FlightDTO flight) {
    this.bookingDate = bookingDate;
    this.startDate = startDate;
    this.totalPrice = totalPrice;
    this.flight = flight;
  }

}
