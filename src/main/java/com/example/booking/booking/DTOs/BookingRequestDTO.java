package com.example.booking.booking.DTOs;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
public abstract class BookingRequestDTO {

  @NotBlank
  private String startDate;

  @NotNull
  private int totalPrice;

  public BookingRequestDTO(String startDate, int totalPrice) {
    this.startDate = startDate;
    this.totalPrice = totalPrice;
  }

  public LocalDateTime getStartDate() {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    return LocalDateTime.parse(startDate, formatter);
  }

}
