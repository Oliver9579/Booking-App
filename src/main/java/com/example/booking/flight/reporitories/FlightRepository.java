package com.example.booking.flight.reporitories;

import com.example.booking.flight.DTOs.FlightOneWayDTO;
import com.example.booking.flight.models.Flight;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface FlightRepository extends CrudRepository<Flight, Integer> {

  List<Flight> findAll();

  Optional<Flight> findById(Integer id);

  @Query(value = "SELECT * FROM flights WHERE origin = :#{#flightOneWay.origin} AND destination = :#{#flightOneWay.destination} AND date(departure_date) = date(:#{#flightOneWay.departureDate})", nativeQuery = true)
  List<Flight> findAllByOriginDestinationAndDepartureTime(@Param("flightOneWay")FlightOneWayDTO flightOneWay);

}
