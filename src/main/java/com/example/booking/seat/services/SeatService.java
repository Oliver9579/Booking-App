package com.example.booking.seat.services;

import com.example.booking.seat.models.Seat;

import java.util.List;

public interface SeatService {

  List<Seat> getSeatsById(List<Integer> seatsId);

  Seat setAvailabilityFalse(Seat seat);

  Seat save(Seat seat);

}
