package com.example.booking.booking.DTOs.bookingFlight;

import com.example.booking.flight.DTOs.FlightDTO;
import lombok.Data;

import java.util.Date;

@Data
public class BookingRoundTripFlightResponseDTO {

  private Date bookingDate;
  private Date startDate;
  private Date endDate;
  private int totalPrice;
  private FlightDTO flightToDestination;
  private FlightDTO flightReturn;

  public BookingRoundTripFlightResponseDTO(Date bookingDate, Date startDate, Date endDate,
                                           int totalPrice, FlightDTO flightToDestination, FlightDTO flightReturn) {
    this.bookingDate = bookingDate;
    this.startDate = startDate;
    this.endDate = endDate;
    this.totalPrice = totalPrice;
    this.flightToDestination = flightToDestination;
    this.flightReturn = flightReturn;
  }
}
