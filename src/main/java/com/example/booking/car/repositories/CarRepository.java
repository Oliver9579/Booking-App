package com.example.booking.car.repositories;

import com.example.booking.car.models.Car;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CarRepository extends CrudRepository<Car, Integer> {

  @Query(value = "SELECT * FROM cars " +
          "WHERE pick_up_location = :#{#pickUpLocation} " +
          "AND drop_off_location = :#{#pickUpLocation} " +
          "AND (:carType is null or car_type = :carType) " +
          "AND (:capacity is null or capacity = :capacity) " +
          "AND (:transmissionType is null or transmission_type = :transmissionType)", nativeQuery = true)
  List<Car> findSameDropOffLocationCar(@Param("pickUpLocation") String pickUpLocation,
                                       @Param("carType") String carType,
                                       @Param("capacity") Integer capacity,
                                       @Param("transmissionType") String transmissionType);

  @Query(value = "SELECT * FROM cars " +
          "WHERE pick_up_location = :#{#pickUpLocation} " +
          "AND drop_off_location = :#{#dropOffLocation} " +
          "AND (:carType is null or car_type = :carType) " +
          "AND (:capacity is null or capacity = :capacity) " +
          "AND (:transmissionType is null or transmission_type = :transmissionType)", nativeQuery = true)
  List<Car> findByPickUpLocationAndDropOffLocation(@Param("pickUpLocation") String pickUpLocation,
                                                   @Param("dropOffLocation") String dropOffLocation,
                                                   @Param("carType") String carType,
                                                   @Param("capacity") Integer capacity,
                                                   @Param("transmissionType") String transmissionType);

}
