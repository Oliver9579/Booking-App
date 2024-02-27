package com.example.booking.booking.services;

import com.example.booking.booking.DTOs.bookingFlight.BookingOneWayFlightRequestDTO;
import com.example.booking.booking.DTOs.bookingFlight.BookingOneWayFlightResponseDTO;
import com.example.booking.booking.models.Booking;
import com.example.booking.booking.repository.BookingRepository;
import com.example.booking.car.services.CarService;
import com.example.booking.flight.models.Flight;
import com.example.booking.flight.services.FlightService;
import com.example.booking.hotel.services.HotelService;
import com.example.booking.seat.services.SeatService;
import com.example.booking.user.models.User;
import com.example.booking.user.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookingServiceImpl implements BookingService {

  private BookingRepository bookingRepository;
  private UserService userService;
  private HotelService hotelService;
  private FlightService flightService;
  private CarService carService;
  private SeatService seatService;

  @Override
  public BookingOneWayFlightResponseDTO createOneWayFlightBooking(User user, BookingOneWayFlightRequestDTO bookingFlight) {
    Flight flight = flightService.getFlightById(bookingFlight.getOutboundFlightId());
    flight.setSeats(seatService.getSeatsById(bookingFlight.getSeatsId()).stream()
            .map(seat -> seatService.setAvailabilityFalse(seat))
            .map(seat -> seatService.save(seat))
            .collect(Collectors.toList()));

    Booking booking = bookingRepository.save(new Booking(bookingFlight.getStartDate(),
            bookingFlight.getTotalPrice(), user, flight, flight.getSeats()));

    return new BookingOneWayFlightResponseDTO(booking.getBookingDate(), booking.getStartDate(), booking.getTotalPrice(),
            flightService.convertToFlightDTO(booking.getOutboundFlight()));
  }
}
