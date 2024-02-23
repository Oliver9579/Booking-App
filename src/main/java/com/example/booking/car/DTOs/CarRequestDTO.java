package com.example.booking.car.DTOs;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public abstract class CarRequestDTO {

  @NotBlank
  private String pickUpLocation;
  @NotBlank
  @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Date must be in yyyy-mm-dd format")
  private String pickUpDate;
  @NotBlank
  @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Date must be in yyyy-mm-dd format")
  private String dropOffDate;

  public CarRequestDTO(String pickUpLocation, String pickUpDate, String dropOffDate) {
    this.pickUpLocation = pickUpLocation;
    this.pickUpDate = pickUpDate;
    this.dropOffDate = dropOffDate;
  }
}
