package com.example.booking.car.repositories;

import com.example.booking.car.models.Car;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CarRepository extends CrudRepository<Car, Integer> {

  @Query(value = "SELECT * FROM cars WHERE pick_up_location = :#{#pickUpLocation} " +
          "AND drop_off_location = :#{#pickUpLocation} ", nativeQuery = true)
  List<Car> findSameDropOffLocationCar(String pickUpLocation);

  List<Car> findByPickUpLocationAndDropOffLocation(String pickUpLocation, String dropOffLocation);

}
