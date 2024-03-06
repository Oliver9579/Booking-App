package com.example.booking.hotel.controllers;

import com.example.booking.hotel.DTOs.AllHotelDTO;
import com.example.booking.hotel.DTOs.HotelListDTO;
import com.example.booking.hotel.DTOs.HotelRequestDTO;
import com.example.booking.hotel.services.HotelService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/hotels")
public class HotelController {

  private HotelService hotelService;

  @GetMapping("/all")
  public ResponseEntity<List<AllHotelDTO>> getAllHotel() {
    return ResponseEntity.ok().body(hotelService.getAllHotel());
  }

  @GetMapping
  public ResponseEntity<HotelListDTO> getHotelsByGivenDetails(@RequestParam String location,
                                                              @RequestParam String checkInDate,
                                                              @RequestParam String checkOutDate,
                                                              @RequestParam Integer guests) {
    HotelRequestDTO hotelRequest = HotelRequestDTO.convertHotelRequestDTO(location, checkInDate, checkOutDate, guests);
    return ResponseEntity.ok().body(hotelService.getAllByLocation(hotelRequest));
  }

}
