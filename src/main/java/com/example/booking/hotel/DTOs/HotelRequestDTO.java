package com.example.booking.hotel.DTOs;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class HotelRequestDTO {

  @NotBlank
  private String location;
  @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Date must be in yyyy-mm-dd format")
  private String checkInDate;
  @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Date must be in yyyy-mm-dd format")
  private String checkOutDate;
  @NotNull
  @Min(value = 1, message = "Please give at least one guest!")
  private Integer guests;

}
