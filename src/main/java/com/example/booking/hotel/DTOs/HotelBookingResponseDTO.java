package com.example.booking.hotel.DTOs;

import com.example.booking.room.models.RoomBookingDTO;
import lombok.Data;

import java.util.List;

@Data
public class HotelBookingResponseDTO {

  private int id;
  private String name;
  private String location;
  private String street;
  private int stars;
  private List<RoomBookingDTO> rooms;

  public HotelBookingResponseDTO(int id, String name, String location, String street, int stars, List<RoomBookingDTO> rooms) {
    this.id = id;
    this.name = name;
    this.location = location;
    this.street = street;
    this.stars = stars;
    this.rooms = rooms;
  }

}