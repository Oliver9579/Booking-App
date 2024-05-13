package com.example.booking.seat.services;

import com.example.booking.booking.models.Booking;
import com.example.booking.exceptions.IdNotFoundException;
import com.example.booking.seat.models.Seat;
import com.example.booking.seat.repositories.SeatRepository;
import com.example.booking.user.models.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
  public Seat save(Seat seat) {
    return seatRepository.save(seat);
  }

  @Override
  public List<Seat> setSeatsAvailabilityFalse(List<Seat> seats) {
    return seats.stream()
            .map(seat -> {
              seat.setAvailability(false);
              seatRepository.save(seat);
              return seat;
            }).collect(Collectors.toList());
  }

  @Override
  public void updateSeatsAvailability(User user) {
    for (Booking booking : user.getBooking()) {
      if (booking.getOutboundFlight() != null) {
        List<Seat> seats = booking.getBookedSeats();
        for (Seat seat : seats) {
          seat.setAvailability(true);
          save(seat);
        }
      }
    }
  }

}
