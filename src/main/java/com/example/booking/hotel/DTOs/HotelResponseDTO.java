package com.example.booking.hotel.DTOs;

import com.example.booking.review.DTOs.ReviewResponseDTO;
import com.example.booking.room.DTOs.RoomDTO;
import lombok.Data;

import java.util.List;

@Data
public class HotelResponseDTO {

  private int id;
  private String name;
  private String location;
  private String street;
  private int stars;
  private int numberOfNights;
  private String img;
  private List<RoomDTO> rooms;
  private List<ReviewResponseDTO> reviews;

  public HotelResponseDTO(int id, String name, String location, String street, int stars, int numberOfNights, String img, List<RoomDTO> rooms, List<ReviewResponseDTO> reviews) {
    this.id = id;
    this.name = name;
    this.location = location;
    this.street = street;
    this.stars = stars;
    this.numberOfNights = numberOfNights;
    this.img = img;
    this.rooms = rooms;
    this.reviews = reviews;
  }
}
