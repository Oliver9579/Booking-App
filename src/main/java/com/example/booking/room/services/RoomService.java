package com.example.booking.room.services;

import com.example.booking.booking.DTOs.bookingHotel.BookingRoomDTO;
import com.example.booking.date.models.Days;
import com.example.booking.hotel.models.Hotel;
import com.example.booking.room.DTOs.RoomDTO;
import com.example.booking.room.models.Room;
import com.example.booking.room.models.RoomType;

import java.util.List;

public interface RoomService {

  Boolean isRoomAvailable(Room room, String checkInDate, String checkOutDate);

  List<RoomDTO> convertToRoomDTO(List<Room> rooms, List<Long> availableNumbers, String checkInDate, String checkOutDate);

  List<Room> getRoomsByType(Hotel hotel, RoomType[] roomTypes);

  List<Long> getRoomsCountByType(List<Room> rooms);

  List<Room> getRoomsById(List<Integer> roomIds);

  List<Room> setUnavailableDates(List<Room> rooms, List<Days> days);

  List<Room> getOneRoomForEachGivenType(List<Room> availableRooms, List<BookingRoomDTO> roomTypesWithNumber);

  Room save(Room room);

}
