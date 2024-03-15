package com.example.booking.car.controllers;

import com.example.booking.car.DTOs.*;
import com.example.booking.car.services.CarService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
@AllArgsConstructor
public class CarController {

  private CarService carService;

  @GetMapping
  public ResponseEntity<List<AllCarsDTO>> getAllCars() {
    return ResponseEntity.ok().body(carService.getAllCars());
  }

  @GetMapping("/dropOff/same")
  private ResponseEntity<CarListDTO> getCarsWithSameDropOffLocation(
          @RequestParam String pickUpLocation, @RequestParam String pickUpDate, @RequestParam String dropOffDate,
          @RequestParam(required = false) String carType,
          @RequestParam(required = false) Integer capacity,
          @RequestParam(required = false) String transmissionType) {
    CarSameDropOffRequestDTO carSameDropOffRequest = CarSameDropOffRequestDTO.convertCarRequest(
            pickUpLocation, pickUpDate, dropOffDate);
    return ResponseEntity.ok().body(carService.getCarsWithSameDropOffLocation(carSameDropOffRequest,
            carType, capacity, transmissionType));
  }

  @GetMapping("/dropOff/different")
  private ResponseEntity<CarListDTO> getCarsWithSameDropOffLocation(
          @RequestParam String pickUpLocation, @RequestParam String dropOffLocation,
          @RequestParam String pickUpDate, @RequestParam String dropOffDate,
          @RequestParam(required = false) String carType,
          @RequestParam(required = false) Integer capacity,
          @RequestParam(required = false) String transmissionType) {
    CarDifferentDropOffRequestDTO carDifferentDropOff = CarDifferentDropOffRequestDTO.convertCarRequest(
            pickUpLocation, dropOffLocation, pickUpDate, dropOffDate);
    return ResponseEntity.ok().body(carService.getCarsWithDifferentDropOffLocation(carDifferentDropOff,
            carType, capacity, transmissionType));
  }

}
