package com.example.booking.car.services;

import com.example.booking.booking.DTOs.bookingCar.BookedCar;
import com.example.booking.car.DTOs.*;
import com.example.booking.car.models.Car;
import com.example.booking.user.models.User;

import java.util.Date;
import java.util.List;

public interface CarService {

  CarListDTO getCarsWithSameDropOffLocation(CarSameDropOffRequestDTO carSameDropOffRequest,
                                            String carType, Integer capacity, String transmissionType);

  CarListDTO getCarsWithDifferentDropOffLocation(CarDifferentDropOffRequestDTO carDifferentDropOff,
                                                 String carType, Integer capacity, String transmissionType);

  Boolean isCarAvailable(Car car, String pickUpDate, String dropOffDate);

  List<AllCarsDTO> getAllCars();

  Car getCarById(Integer id);

  CarDTO convertCarToCarDTO(Car car, int travelLength, Date pickUpDate, Date dropOffDate);

  Car save(Car car);

  List<BookedCar> getBookedCarsWithUnavailableDays(User user);

  void updateCarAvailability(List<BookedCar> bookedCars);

}
