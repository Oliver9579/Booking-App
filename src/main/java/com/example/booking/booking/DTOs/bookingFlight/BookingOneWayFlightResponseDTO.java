package com.example.booking.booking.DTOs.bookingFlight;

import com.example.booking.booking.DTOs.BookingResponseDTO;
import com.example.booking.flight.DTOs.FlightDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class BookingOneWayFlightResponseDTO extends BookingResponseDTO {

  private FlightDTO flight;

  public BookingOneWayFlightResponseDTO(Date bookingDate, Date startDate, int totalPrice, FlightDTO flight) {
    super(bookingDate, startDate, totalPrice);
    this.flight = flight;
  }

}
