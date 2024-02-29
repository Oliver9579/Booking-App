package com.example.booking.hotel.services;

import com.example.booking.hotel.DTOs.HotelBookingResponseDTO;
import com.example.booking.hotel.DTOs.HotelListDTO;
import com.example.booking.hotel.DTOs.HotelRequestDTO;
import com.example.booking.hotel.DTOs.HotelResponseDTO;
import com.example.booking.hotel.models.Hotel;

import java.util.Optional;

public interface HotelService {

  HotelListDTO getAllByLocation(HotelRequestDTO hotelRequest);

  Hotel getHotelById(Integer id);

  HotelBookingResponseDTO convertToResponseDTO(Hotel hotel);

}
