package com.example.booking.booking.DTOs.bookingCar;

import com.example.booking.car.models.Car;
import com.example.booking.date.models.Days;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class BookedCar {

  private Car car;
  private Set<Days> days;

}