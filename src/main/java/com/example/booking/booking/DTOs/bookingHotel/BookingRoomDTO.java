package com.example.booking.booking.DTOs.bookingHotel;

import com.example.booking.room.models.RoomType;
import lombok.Data;

@Data
public class BookingRoomDTO {

  private RoomType roomType;
  private int roomCount;

  public BookingRoomDTO(RoomType roomType, int roomCount) {
    this.roomType = roomType;
    this.roomCount = roomCount;
  }
}
