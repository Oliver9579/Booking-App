package com.example.booking.hotel.DTOs;

import com.example.booking.review.DTOs.ReviewResponseDTO;
import lombok.Data;

import java.util.List;

@Data
public class AllHotelDTO {

  private int id;
  private String name;
  private String location;
  private String street;
  private int stars;
  private String img;
  private List<ReviewResponseDTO> reviews;

  public AllHotelDTO(int id, String name, String location, String street, int stars, String img, List<ReviewResponseDTO> reviews) {
    this.id = id;
    this.name = name;
    this.location = location;
    this.street = street;
    this.stars = stars;
    this.img = img;
    this.reviews = reviews;
  }
}
