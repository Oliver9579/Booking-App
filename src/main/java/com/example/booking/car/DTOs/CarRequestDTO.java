package com.example.booking.car.DTOs;

import lombok.Data;

@Data
public abstract class CarRequestDTO {

  private String pickUpLocation;
  private String pickUpDate;
  private String dropOffDate;

  public CarRequestDTO(String pickUpLocation, String pickUpDate, String dropOffDate) {
    this.pickUpLocation = pickUpLocation;
    this.pickUpDate = pickUpDate;
    this.dropOffDate = dropOffDate;
  }
}
