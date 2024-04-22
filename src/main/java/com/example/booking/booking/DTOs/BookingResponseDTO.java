package com.example.booking.booking.DTOs;

import lombok.Data;

import java.util.Date;

@Data
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