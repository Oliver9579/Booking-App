package com.example.booking.car.controllers;

import com.example.booking.car.DTOs.CarDifferentDropOffDTO;
import com.example.booking.car.DTOs.CarListDTO;
import com.example.booking.car.DTOs.CarSameDropOffRequestDTO;
import com.example.booking.car.services.CarService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/cars")
@AllArgsConstructor
public class CarController {

  private CarService carService;

  @GetMapping("/dropOff/same")
  private ResponseEntity<CarListDTO> getCarsWithSameDropOffLocation(
          @Valid @RequestBody CarSameDropOffRequestDTO carSameDropOffRequest,
          @RequestParam(required = false) String carType,
          @RequestParam(required = false) Integer capacity,
          @RequestParam(required = false) String transmissionType) {
    return ResponseEntity.ok().body(carService.getCarsWithSameDropOffLocation(carSameDropOffRequest,
            carType, capacity, transmissionType));
  }

  @GetMapping("/dropOff/different")
  private ResponseEntity<CarListDTO> getCarsWithSameDropOffLocation(
          @Valid @RequestBody CarDifferentDropOffDTO carDifferentDropOff,
          @RequestParam(required = false) String carType,
          @RequestParam(required = false) Integer capacity,
          @RequestParam(required = false) String transmissionType) {
    return ResponseEntity.ok().body(carService.getCarsWithDifferentDropOffLocation(carDifferentDropOff,
            carType, capacity, transmissionType));
  }

}
