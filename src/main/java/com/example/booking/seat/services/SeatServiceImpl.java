package com.example.booking.seat.services;

import com.example.booking.exceptions.IdNotFoundException;
import com.example.booking.seat.models.Seat;
import com.example.booking.seat.repositories.SeatRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class SeatServiceImpl implements SeatService {

  private SeatRepository seatRepository;

  @Override
  public List<Seat> getSeatsById(List<Integer> seatsId) {
    List<Seat> seats = new ArrayList<>();
    for (int id : seatsId) {
      seats.add(seatRepository.findById(id).orElseThrow(IdNotFoundException::new));
    }
    return seats;
  }

  @Override
  public Seat setAvailabilityFalse(Seat seat) {
    seat.setAvailability(false);
    return seat;
  }

  @Override
  public Seat save(Seat seat) {
    return seatRepository.save(seat);
  }

}
