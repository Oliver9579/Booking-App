package com.example.booking.hotel.services;

import com.example.booking.hotel.repositories.HotelRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class HotelServiceImpl implements HotelService {

  private HotelRepository hotelRepository;

}
