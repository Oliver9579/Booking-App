package com.example.booking.car.services;

import com.example.booking.car.DTOs.CarDifferentDropOffDTO;
import com.example.booking.car.DTOs.CarListDTO;
import com.example.booking.car.DTOs.CarSameDropOffRequestDTO;
import com.example.booking.car.models.Car;

public interface CarService {

  CarListDTO getCarsWithSameDropOffLocation(CarSameDropOffRequestDTO carSameDropOffRequest,
                                            String carType, Integer capacity, String transmissionType);

  CarListDTO getCarsWithDifferentDropOffLocation(CarDifferentDropOffDTO carDifferentDropOff,
                                                 String carType, Integer capacity, String transmissionType);

  Boolean isCarAvailable(Car car, String pickUpDate, String dropOffDate);

}
