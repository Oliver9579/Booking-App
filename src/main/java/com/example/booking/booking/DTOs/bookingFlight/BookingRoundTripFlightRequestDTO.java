package com.example.booking.booking.DTOs.bookingFlight;

import com.example.booking.booking.DTOs.BookingRequestDTO;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class BookingRoundTripFlightRequestDTO extends BookingRequestDTO {

  @NotNull
  private int outboundFlightId;
  @NotNull
  private int returnFlightId;
  @NotBlank
  private String endDate;
  @NotNull
  private List<Integer> outboundFlightSeatsId;
  @NotNull
  private List<Integer> returnFlightSeatsId;

  public BookingRoundTripFlightRequestDTO(String startDate, int totalPrice, int outboundFlightId, int returnFlightId,
                                          String endDate, List<Integer> outboundFlightSeatsId, List<Integer> returnFlightSeatsId) {
    super(startDate, totalPrice);
    this.outboundFlightId = outboundFlightId;
    this.returnFlightId = returnFlightId;
    this.endDate = endDate;
    this.outboundFlightSeatsId = outboundFlightSeatsId;
    this.returnFlightSeatsId = returnFlightSeatsId;
  }

  public Date getEndDate() {
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    Date date = new Date();
    try {
      date = formatter.parse(endDate);
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return date;
  }
}
