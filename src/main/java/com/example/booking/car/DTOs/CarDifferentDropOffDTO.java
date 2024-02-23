package com.example.booking.car.DTOs;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class CarDifferentDropOffDTO extends CarRequestDTO {

  @NotBlank
  private String dropOffLocation;

  public CarDifferentDropOffDTO(String pickUpLocation, String dropOffLocation, String pickUpDate, String dropOffDate) {
    super(pickUpLocation, pickUpDate, dropOffDate);
    this.dropOffLocation = dropOffLocation;
  }
}
