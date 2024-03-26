package com.example.booking.flight.reporitories;

import com.example.booking.flight.DTOs.FlightOneWayRequestDTO;
import com.example.booking.flight.DTOs.FlightRoundTripRequestDTO;
import com.example.booking.flight.models.Flight;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FlightRepository extends CrudRepository<Flight, Integer> {

  @Query(value = "SELECT * FROM flights LIMIT 20", nativeQuery = true)
  List<Flight> findAll();

  Optional<Flight> findById(Integer id);

  @Query(value = "SELECT * FROM flights WHERE origin = :#{#flightOneWay.origin} AND destination = :#{#flightOneWay.destination} AND date(departure_date) = date(:#{#flightOneWay.departureDate})", nativeQuery = true)
  List<Flight> findAllByOriginDestinationAndDepartureTime(@Param("flightOneWay") FlightOneWayRequestDTO flightOneWay);

  @Query(value = "SELECT f1.id as id1, f2.id as id2 FROM flights f1, flights f2 " +
          "WHERE f1.origin = :#{#flightReturn.origin} " +
          "AND f1.destination = :#{#flightReturn.destination} " +
          "AND date(f1.departure_date) = date(:#{#flightReturn.departureDate}) " +
          "AND date(f2.departure_date) = date(:#{#flightReturn.returnDate}) " +
          "AND f1.origin = f2.destination AND f1.destination = f2.origin", nativeQuery = true)
  List<List<Object>> findByDestination(@Param("flightReturn") FlightRoundTripRequestDTO flightRoundTripRequest);


}
