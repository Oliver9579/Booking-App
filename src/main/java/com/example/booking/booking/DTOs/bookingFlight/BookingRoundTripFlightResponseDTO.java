package com.example.booking.booking.DTOs.bookingFlight;

import com.example.booking.booking.DTOs.BookingResponseDTO;
import com.example.booking.flight.DTOs.FlightDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class BookingRoundTripFlightResponseDTO extends BookingResponseDTO {

  private Date endDate;
  private FlightDTO flightToDestination;
  private FlightDTO flightReturn;


  public BookingRoundTripFlightResponseDTO(Date bookingDate, Date startDate, int totalPrice, Date endDate,
                                           FlightDTO flightToDestination, FlightDTO flightReturn) {
    super(bookingDate, startDate, totalPrice);
    this.endDate = endDate;
    this.flightToDestination = flightToDestination;
    this.flightReturn = flightReturn;
  }
}
