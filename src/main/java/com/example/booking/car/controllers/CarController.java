package com.example.booking.car.controllers;

import com.example.booking.car.services.CarService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cars")
@AllArgsConstructor
public class CarController {

  private CarService carService;

}
