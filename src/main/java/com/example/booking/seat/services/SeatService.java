package com.example.booking.seat.services;

import com.example.booking.seat.models.Seat;
import com.example.booking.user.models.User;

import java.util.List;

public interface SeatService {

  List<Seat> getSeatsById(List<Integer> seatsId);

  Seat save(Seat seat);

  List<Seat> setSeatsAvailabilityFalse(List<Seat> seats);

  void updateSeatsAvailability(User user);

}
