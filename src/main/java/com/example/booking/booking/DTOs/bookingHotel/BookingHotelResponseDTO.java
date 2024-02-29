package com.example.booking.booking.DTOs.bookingHotel;

import com.example.booking.hotel.DTOs.HotelBookingResponseDTO;
import lombok.Data;

import java.util.Date;

@Data
public class BookingHotelResponseDTO {

  private Date bookingDate;
  private Date startDate;
  private Date endDate;
  private int totalPrice;
  private HotelBookingResponseDTO hotel;

  public BookingHotelResponseDTO(Date bookingDate, Date startDate, Date endDate, int totalPrice, HotelBookingResponseDTO hotel) {
    this.bookingDate = bookingDate;
    this.startDate = startDate;
    this.endDate = endDate;
    this.totalPrice = totalPrice;
    this.hotel = hotel;
  }

}