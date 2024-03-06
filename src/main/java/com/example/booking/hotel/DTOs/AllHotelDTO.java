package com.example.booking.hotel.DTOs;

import lombok.Data;

@Data
public class AllHotelDTO {

  private int id;
  private String name;
  private String location;
  private String street;
  private int stars;
  private String img;

  public AllHotelDTO(int id, String name, String location, String street, int stars, String img) {
    this.id = id;
    this.name = name;
    this.location = location;
    this.street = street;
    this.stars = stars;
    this.img = img;
  }
}
