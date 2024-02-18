package com.example.booking.hotel.DTOs;

import lombok.Data;

import java.util.List;

@Data
public class HotelListDTO {

  private List<HotelResponseDTO> hotels;

  public HotelListDTO(List<HotelResponseDTO> hotels) {
    this.hotels = hotels;
  }
}
