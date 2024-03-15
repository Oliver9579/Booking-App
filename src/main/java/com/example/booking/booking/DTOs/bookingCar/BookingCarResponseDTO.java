package com.example.booking.booking.DTOs.bookingCar;

import com.example.booking.car.DTOs.CarDTO;
import lombok.Data;

import java.util.Date;

@Data
public class BookingCarResponseDTO {

  private Date bookingDate;
  private Date startDate;
  private Date endDate;
  private int totalPrice;
  private CarDTO car;

  public BookingCarResponseDTO(Date bookingDate, Date startDate, Date endDate, int totalPrice, CarDTO car) {
    this.bookingDate = bookingDate;
    this.startDate = startDate;
    this.endDate = endDate;
    this.totalPrice = totalPrice;
    this.car = car;
  }
}
