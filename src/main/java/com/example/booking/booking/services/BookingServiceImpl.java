package com.example.booking.booking.services;

import com.example.booking.booking.DTOs.bookingFlight.BookingOneWayFlightRequestDTO;
import com.example.booking.booking.DTOs.bookingFlight.BookingOneWayFlightResponseDTO;
import com.example.booking.booking.DTOs.bookingFlight.BookingRoundTripFlightRequestDTO;
import com.example.booking.booking.DTOs.bookingFlight.BookingRoundTripFlightResponseDTO;
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
import java.util.stream.Stream;

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
    flight.setSeats(seatService.setSeatsAvailabilityFalse(seatService.getSeatsById(bookingFlight.getSeatsId())));

    Booking booking = bookingRepository.save(new Booking(bookingFlight.getStartDate(),
            bookingFlight.getTotalPrice(), user, flight));

    return new BookingOneWayFlightResponseDTO(booking.getBookingDate(), booking.getStartDate(), booking.getTotalPrice(),
            flightService.convertToFlightDTO(booking.getOutboundFlight()));
  }

  @Override
  public BookingRoundTripFlightResponseDTO createRoundTripFlightBooking(
          User user, BookingRoundTripFlightRequestDTO bookingFlight) {
    Flight flightToDestination = flightService.getFlightById(bookingFlight.getOutboundFlightId());
    Flight flightReturn = flightService.getFlightById(bookingFlight.getReturnFlightId());

    flightToDestination.setSeats(seatService.setSeatsAvailabilityFalse(
            seatService.getSeatsById(bookingFlight.getOutboundFlightSeatsId())));

    flightReturn.setSeats(seatService.setSeatsAvailabilityFalse(
            seatService.getSeatsById(bookingFlight.getReturnFlightSeatsId())));


    Booking booking = bookingRepository.save(new Booking(bookingFlight.getStartDate(), bookingFlight.getEndDate(),
            bookingFlight.getTotalPrice(), user, flightToDestination, flightReturn));

    return new BookingRoundTripFlightResponseDTO(
            booking.getBookingDate(), booking.getStartDate(), booking.getEndDate(), booking.getTotalPrice(),
            flightService.convertToFlightDTO(booking.getOutboundFlight()),
            flightService.convertToFlightDTO(booking.getReturnFlight()));
  }

}
