package com.example.booking.booking.services;

import com.example.booking.booking.repository.BookingRepository;
import com.example.booking.car.services.CarService;
import com.example.booking.flight.services.FlightService;
import com.example.booking.hotel.services.HotelService;
import com.example.booking.user.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BookingServiceImpl implements BookingService {

  private BookingRepository bookingRepository;
  private UserService userService;
  private HotelService hotelService;
  private FlightService flightService;
  private CarService carService;

}
