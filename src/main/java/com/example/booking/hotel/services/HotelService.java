package com.example.booking.hotel.services;

import com.example.booking.hotel.DTOs.HotelListDTO;
import com.example.booking.hotel.DTOs.HotelRequestDTO;
import com.example.booking.hotel.models.Hotel;
import com.example.booking.room.models.Room;
import com.example.booking.room.models.RoomType;

import java.util.List;

public interface HotelService {

  HotelListDTO getAllByLocation(HotelRequestDTO hotelRequest);

  List<Hotel> getHotelsWithAvailableRoomsByRoomType(HotelRequestDTO hotelRequest, List<RoomType> roomType);

  List<Room> getRandomRoomsFromEveryAvailableRoom(List<Room> availableRooms, List<RoomType> roomType);


}
