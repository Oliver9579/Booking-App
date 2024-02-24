package com.example.booking.hotel.services;

import com.example.booking.hotel.DTOs.HotelListDTO;
import com.example.booking.hotel.DTOs.HotelRequestDTO;

public interface HotelService {

  HotelListDTO getAllByLocation(HotelRequestDTO hotelRequest);

}
