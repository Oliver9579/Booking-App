package com.example.booking.seat.services;

import com.example.booking.seat.repositories.SeatRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SeatServiceImpl implements SeatService {

  private SeatRepository seatRepository;

}
