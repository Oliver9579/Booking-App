package com.example.booking.car.controllers;

import com.example.booking.car.DTOs.CarDTO;
import com.example.booking.car.DTOs.CarDifferentDropOffDTO;
import com.example.booking.car.DTOs.CarListDTO;
import com.example.booking.car.DTOs.CarSameDropOffRequestDTO;
import com.example.booking.car.services.CarService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/cars")
@AllArgsConstructor
public class CarController {

  private CarService carService;

  @GetMapping("/dropOff/same")
  private ResponseEntity<CarListDTO> getCarsWithSameDropOffLocation(
          @Valid @RequestBody CarSameDropOffRequestDTO carSameDropOffRequest) {
    return ResponseEntity.ok().body(carService.getCarsWithSameDropOffLocation(carSameDropOffRequest));
  }

  /*@GetMapping("/dropOff/different")
  private ResponseEntity<List<CarDTO>> getCarsWithSameDropOffLocation(
          @Valid @RequestBody CarDifferentDropOffDTO carDifferentDropOff) {
    return ResponseEntity.ok().body(carService.)
  }*/

}
