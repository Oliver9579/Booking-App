package com.example.booking.hotel.controllers;

import com.example.booking.hotel.DTOs.HotelListDTO;
import com.example.booking.hotel.DTOs.HotelRequestDTO;
import com.example.booking.hotel.services.HotelService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping("/api/hotels")
public class HotelController {

  private HotelService hotelService;

  @GetMapping
  public ResponseEntity<HotelListDTO> getHotelsByGivenDetails(@Valid @RequestBody HotelRequestDTO hotelRequest) {
    return ResponseEntity.ok().body(hotelService.getAllByLocation(hotelRequest));
  }

}
