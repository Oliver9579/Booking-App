package com.example.booking.car.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class CarSameDropOffRequestDTO {

  @NotBlank
  private String pickUpLocation;
  @NotBlank
  @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Date must be in yyyy-mm-dd format")
  private String pickUpDate;
  @NotBlank
  @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Date must be in yyyy-mm-dd format")
  private String dropOffDate;

}
