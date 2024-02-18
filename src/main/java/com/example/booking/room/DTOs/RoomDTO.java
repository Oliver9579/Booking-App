package com.example.booking.room.DTOs;

import com.example.booking.room.models.RoomType;
import lombok.Data;

@Data
public class RoomDTO {

  private int id;
  private RoomType roomType;
  private int capacity;
  private int pricePerNight;

  public RoomDTO(int id, RoomType roomType, int capacity, int pricePerNight) {
    this.id = id;
    this.roomType = roomType;
    this.capacity = capacity;
    this.pricePerNight = pricePerNight;
  }
}
