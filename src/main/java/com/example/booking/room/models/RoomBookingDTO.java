package com.example.booking.room.models;

import lombok.Data;

@Data
public class RoomBookingDTO {

  private int id;
  private RoomType roomType;
  private int capacity;
  private int pricePerNight;

  public RoomBookingDTO(int id, RoomType roomType, int capacity, int pricePerNight) {
    this.id = id;
    this.roomType = roomType;
    this.capacity = capacity;
    this.pricePerNight = pricePerNight;
  }

}