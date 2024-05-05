package com.example.booking.booking.DTOs;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public abstract class BookingResponseDTO {

  private Date bookingDate;
  private Date startDate;
  private int totalPrice;

  public BookingResponseDTO(Date bookingDate, Date startDate, int totalPrice) {
    this.bookingDate = bookingDate;
    this.startDate = startDate;
    this.totalPrice = totalPrice;
  }

}