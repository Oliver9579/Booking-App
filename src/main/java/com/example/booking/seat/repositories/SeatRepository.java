package com.example.booking.seat.repositories;

import com.example.booking.seat.models.Seat;
import org.springframework.data.repository.CrudRepository;

public interface SeatRepository extends CrudRepository<Seat, Integer> {

}
