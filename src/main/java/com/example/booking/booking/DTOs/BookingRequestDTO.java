package com.example.booking.booking.DTOs;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public abstract class BookingRequestDTO {

  @NotBlank
  private String startDate;

  @NotNull
  private int totalPrice;

  public BookingRequestDTO(String startDate, int totalPrice) {
    this.startDate = startDate;
    this.totalPrice = totalPrice;
  }

  public Date getStartDate() {
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    Date date = new Date();
    try {
      date = formatter.parse(startDate);
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return date;
  }

  public String getStartDateInString() {
    return startDate;
  }

}
