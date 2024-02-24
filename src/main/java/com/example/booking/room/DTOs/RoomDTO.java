package com.example.booking.room.DTOs;

import com.example.booking.room.models.RoomType;
import lombok.Data;

@Data
public class RoomDTO {

  private int id;
  private RoomType roomType;
  private int capacity;
  private int fullPrice;
  private Long availableNumber;


  public RoomDTO(int id, RoomType roomType, int capacity, int fullPrice, Long availableNumber) {
    this.id = id;
    this.roomType = roomType;
    this.capacity = capacity;
    this.fullPrice = fullPrice;
    this.availableNumber = availableNumber;
  }
}
