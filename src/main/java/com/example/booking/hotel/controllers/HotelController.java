package com.example.booking.hotel.controllers;

import com.example.booking.hotel.services.HotelService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/hotels")
public class HotelController {

  private HotelService hotelService;

}
