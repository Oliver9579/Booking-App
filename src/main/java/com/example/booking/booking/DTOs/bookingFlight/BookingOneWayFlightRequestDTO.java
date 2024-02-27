package com.example.booking.booking.DTOs.bookingFlight;

import com.example.booking.booking.DTOs.BookingRequestDTO;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
public class BookingOneWayFlightRequestDTO extends BookingRequestDTO {

  @NotNull
  private int outboundFlightId;
  @NotNull
  private List<Integer> seatsId;

  public BookingOneWayFlightRequestDTO(String startDate, int totalPrice, int outboundFlightId, List<Integer> seatsId) {
    super(startDate, totalPrice);
    this.outboundFlightId = outboundFlightId;
    this.seatsId = seatsId;
  }

}
