package com.example.booking.car.DTOs;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class CarDifferentDropOffRequestDTO extends CarRequestDTO {

  @NotBlank
  private String dropOffLocation;

  public CarDifferentDropOffRequestDTO(String pickUpLocation, String dropOffLocation, String pickUpDate, String dropOffDate) {
    super(pickUpLocation, pickUpDate, dropOffDate);
    this.dropOffLocation = dropOffLocation;
  }

  public static CarDifferentDropOffRequestDTO convertCarRequest(String pickUpLocation, String dropOffLocation, String pickUpDate, String dropOffDate) {
    return new CarDifferentDropOffRequestDTO(pickUpLocation, dropOffLocation, pickUpDate, dropOffDate);
  }

}
