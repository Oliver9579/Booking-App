package com.example.booking.car.services;

import com.example.booking.car.DTOs.CarDifferentDropOffDTO;
import com.example.booking.car.DTOs.CarListDTO;
import com.example.booking.car.DTOs.CarSameDropOffRequestDTO;
import com.example.booking.car.models.Car;

public interface CarService {

  CarListDTO getCarsWithSameDropOffLocation(CarSameDropOffRequestDTO carSameDropOffRequest);

  CarListDTO getCarsWithDifferentDropOffLocation(CarDifferentDropOffDTO carDifferentDropOff);

  Boolean isCarAvailable(Car car, String pickUpDate, String dropOffDate);

}
