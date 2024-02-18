package com.example.booking.room.services;

import com.example.booking.hotel.models.Hotel;
import com.example.booking.room.DTOs.RoomDTO;
import com.example.booking.room.models.Room;
import com.example.booking.room.models.RoomType;

import java.util.List;

public interface RoomService {

  Boolean isRoomAvailable(Room room, String checkInDate, String checkOutDate);

  List<RoomDTO> convertToRoomDTO(List<Room> rooms);

}
