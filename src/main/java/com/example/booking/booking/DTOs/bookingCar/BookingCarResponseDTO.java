package com.example.booking.booking.DTOs.bookingCar;

import com.example.booking.booking.DTOs.BookingResponseDTO;
import com.example.booking.car.DTOs.CarDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class BookingCarResponseDTO extends BookingResponseDTO {

  private Date endDate;
  private CarDTO car;


  public BookingCarResponseDTO(Date bookingDate, Date startDate, int totalPrice, Date endDate, CarDTO car) {
    super(bookingDate, startDate, totalPrice);
    this.endDate = endDate;
    this.car = car;
  }
}
