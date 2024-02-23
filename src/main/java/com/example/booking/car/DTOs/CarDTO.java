package com.example.booking.car.DTOs;

import com.example.booking.car.models.CarType;
import com.example.booking.car.models.TransmissionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarDTO {

  private int id;
  private String brand;
  private String model;
  private CarType carType;
  private int capacity;
  private TransmissionType transmissionType;
  private String pickUpLocation;
  private String dropOffLocation;
  private LocalDate pickUpDate;
  private LocalDate dropOffDate;
  private int fullPrice;

}
