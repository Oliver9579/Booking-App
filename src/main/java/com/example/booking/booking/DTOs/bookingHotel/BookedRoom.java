package com.example.booking.booking.DTOs.bookingHotel;

import com.example.booking.date.models.Days;
import com.example.booking.room.models.Room;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class BookedRoom {

  private Room room;
  private Set<Days> days;

}
