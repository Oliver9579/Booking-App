package com.example.booking.car.services;

import com.example.booking.car.repositories.CarRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CarServiceImpl implements CarService {

  private CarRepository carRepository;


}
