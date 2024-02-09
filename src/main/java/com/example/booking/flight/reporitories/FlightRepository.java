package com.example.booking.flight.reporitories;

import com.example.booking.flight.models.Flight;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FlightRepository extends CrudRepository<Flight, Integer> {

  List<Flight> findAll();

}
