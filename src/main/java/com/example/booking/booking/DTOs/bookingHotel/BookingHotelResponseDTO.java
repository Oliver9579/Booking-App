package com.example.booking.booking.DTOs.bookingHotel;

import com.example.booking.booking.DTOs.BookingResponseDTO;
import com.example.booking.hotel.DTOs.HotelBookingResponseDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class BookingHotelResponseDTO extends BookingResponseDTO {

  private Date endDate;
  private HotelBookingResponseDTO hotel;


  public BookingHotelResponseDTO(Date bookingDate, Date startDate, int totalPrice,
                                 Date endDate, HotelBookingResponseDTO hotel) {
    super(bookingDate, startDate, totalPrice);
    this.endDate = endDate;
    this.hotel = hotel;
  }
}