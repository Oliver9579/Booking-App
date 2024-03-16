package com.example.booking.car.services;

import com.example.booking.car.DTOs.*;
import com.example.booking.car.models.Car;
import com.example.booking.car.repositories.CarRepository;
import com.example.booking.date.services.DaysService;
import com.example.booking.exceptions.NoAvailableCarException;
import com.example.booking.exceptions.NoCarFoundException;
import com.example.booking.exceptions.SameDateException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CarServiceImpl implements CarService {

  private CarRepository carRepository;
  private DaysService daysService;


  @Override
  public CarListDTO getCarsWithSameDropOffLocation(CarSameDropOffRequestDTO carSameDropOffRequest,
                                                   String carType, Integer capacity, String transmissionType) {
    if (carSameDropOffRequest.getPickUpDateString().equals(carSameDropOffRequest.getDropOffDateString())) throw new SameDateException();
    List<Car> cars = carRepository.findSameDropOffLocationCar(carSameDropOffRequest.getPickUpLocation(),
            carType, capacity, transmissionType);
    List<Car> availableCars = getCarsByDates(carSameDropOffRequest, cars);
    if (availableCars.isEmpty()) throw new NoAvailableCarException();
    return convertCarsToCarListDTO(availableCars,
            daysService.getFullTravelDates(carSameDropOffRequest.getPickUpDate(),
                    carSameDropOffRequest.getDropOffDate()).size(),
            carSameDropOffRequest.getPickUpDate(), carSameDropOffRequest.getDropOffDate());
  }

  @Override
  public CarListDTO getCarsWithDifferentDropOffLocation(CarDifferentDropOffRequestDTO carDifferentDropOff,
                                                        String carType, Integer capacity, String transmissionType) {
    if (carDifferentDropOff.getPickUpDateString().equals(carDifferentDropOff.getDropOffDateString())) throw new SameDateException();
    List<Car> cars = carRepository.findByPickUpLocationAndDropOffLocation(
            carDifferentDropOff.getPickUpLocation(), carDifferentDropOff.getDropOffLocation(),
            carType, capacity, transmissionType);
    List<Car> availableCars = getCarsByDates(carDifferentDropOff, cars);
    if (availableCars.isEmpty()) throw new NoAvailableCarException();
    return convertCarsToCarListDTO(availableCars,
            daysService.getFullTravelDates(carDifferentDropOff.getPickUpDate(),
                    carDifferentDropOff.getDropOffDate()).size(),
            carDifferentDropOff.getPickUpDate(), carDifferentDropOff.getDropOffDate());

  }

  @Override
  public Boolean isCarAvailable(Car car, String pickUpDate, String dropOffDate) {
    boolean isAvailable = true;
    List<String> travelDates = daysService.getFullTravelDates(pickUpDate, dropOffDate);
    for (String travelDate : travelDates) {
      if (car.getUnavailable().stream().anyMatch(days -> days.getDate().equals(travelDate))) {
        isAvailable = false;
      }
    }
    return isAvailable;
  }

  @Override
  public List<AllCarsDTO> getAllCars() {
    List<Car> cars = carRepository.findAll();
    if (cars.isEmpty()) throw new NoCarFoundException();
    return cars.stream().map(car -> new AllCarsDTO(car.getId(), car.getBrand(), car.getModel(), car.getCarType(),
            car.getCapacity(), car.getTransmissionType(), car.getPickUpLocation(), car.getDropOffLocation(),
            car.getPricePerDay(), car.getImg())).collect(Collectors.toList());

  }

  @Override
  public Car getCarById(Integer id) {
    return carRepository.findById(id).orElseThrow(NoCarFoundException::new);
  }

  @Override
  public CarDTO convertCarToCarDTO(Car car, int travelLength, Date pickUpDate, Date dropOffDate) {
    return new CarDTO(car.getId(), car.getBrand(), car.getModel(), car.getCarType(),
            car.getCapacity(), car.getTransmissionType(), car.getPickUpLocation(),
            car.getDropOffLocation(), pickUpDate, dropOffDate,
            car.getPricePerDay() * travelLength, car.getImg());
  }

  private List<Car> getCarsByDates(CarRequestDTO carRequest, List<Car> cars) {
    if (cars.isEmpty()) throw new NoCarFoundException();
    if (carRequest.getPickUpDate().equals(carRequest.getDropOffDate())) throw new SameDateException();
    return cars.stream()
            .filter(car -> isCarAvailable(car,
                    carRequest.getPickUpDateString(),
                    carRequest.getDropOffDateString())).collect(Collectors.toList());
  }

  private CarListDTO convertCarsToCarListDTO(List<Car> cars, int travelLength, Date pickUpDate, Date dropOffDate) {
    return new CarListDTO(cars.stream().map(car -> convertCarToCarDTO(car, travelLength, pickUpDate, dropOffDate)).collect(Collectors.toList()));
  }
}
