package com.example.booking.hotel.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class HotelDTO {

  private Integer id;
  private String name;
  private String location;
  private int stars;

}
