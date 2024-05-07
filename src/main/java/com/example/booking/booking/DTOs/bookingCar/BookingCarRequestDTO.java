package com.example.booking.booking.DTOs.bookingCar;

import com.example.booking.booking.DTOs.BookingRequestDTO;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@Setter
public class BookingCarRequestDTO extends BookingRequestDTO {

  @NotNull
  private String endDate;
  @NotNull
  private int carId;

  public BookingCarRequestDTO(String startDate, int totalPrice, String endDate, int carId) {
    super(startDate, totalPrice);
    this.endDate = endDate;
    this.carId = carId;
  }

  public Date getEndDate() {
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    Date date = new Date();
    try {
      date = formatter.parse(endDate);
    } catch (ParseException e) {
    }
    return date;
  }

  public String getEndDateInString() {
    return endDate;
  }

}
