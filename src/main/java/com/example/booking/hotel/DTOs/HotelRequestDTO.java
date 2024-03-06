package com.example.booking.hotel.DTOs;

import com.example.booking.exceptions.FormInputIsEmpty;
import lombok.Data;

@Data
public class HotelRequestDTO {

  private String location;
  private String checkInDate;
  private String checkOutDate;
  private Integer guests;

  public HotelRequestDTO(String location, String checkInDate, String checkOutDate, Integer guests) {
    this.location = location;
    this.checkInDate = checkInDate;
    this.checkOutDate = checkOutDate;
    this.guests = guests;
  }

  public static HotelRequestDTO convertHotelRequestDTO(String location, String checkInDate,
                                                       String checkOutDate, Integer guests) {
    validate(location, checkInDate, checkOutDate, guests);
    return new HotelRequestDTO(location, checkInDate, checkOutDate, guests);
  }

  private static void validate(String location, String checkInDate,
                               String checkOutDate, Integer guests) {
    if (location.isBlank() || checkInDate.isBlank() || checkOutDate.isBlank() || guests == null) {
      throw new FormInputIsEmpty();
    }
  }

}
