package com.example.booking.hotel.services;

import com.example.booking.hotel.DTOs.AllHotelDTO;
import com.example.booking.hotel.DTOs.HotelBookingResponseDTO;
import com.example.booking.hotel.DTOs.HotelListDTO;
import com.example.booking.hotel.DTOs.HotelRequestDTO;
import com.example.booking.hotel.models.Hotel;
import com.example.booking.room.models.Room;

import java.util.List;

public interface HotelService {

  HotelListDTO getAllByLocation(HotelRequestDTO hotelRequest);

  List<AllHotelDTO> getAllHotel();

  Hotel getHotelById(Integer id);

  HotelBookingResponseDTO convertToHotelBookingResponseDTO(Hotel hotel, List<Room> rooms);

}
