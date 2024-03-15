package com.example.booking.booking.services;

import com.example.booking.booking.DTOs.bookingCar.BookingCarRequestDTO;
import com.example.booking.booking.DTOs.bookingCar.BookingCarResponseDTO;
import com.example.booking.booking.DTOs.bookingFlight.BookingOneWayFlightRequestDTO;
import com.example.booking.booking.DTOs.bookingFlight.BookingOneWayFlightResponseDTO;
import com.example.booking.booking.DTOs.bookingFlight.BookingRoundTripFlightRequestDTO;
import com.example.booking.booking.DTOs.bookingFlight.BookingRoundTripFlightResponseDTO;
import com.example.booking.booking.DTOs.bookingHotel.BookingHotelRequestDTO;
import com.example.booking.booking.DTOs.bookingHotel.BookingHotelResponseDTO;
import com.example.booking.booking.models.Booking;
import com.example.booking.booking.repository.BookingRepository;
import com.example.booking.car.models.Car;
import com.example.booking.car.services.CarService;
import com.example.booking.date.models.Days;
import com.example.booking.date.services.DaysService;
import com.example.booking.flight.models.Flight;
import com.example.booking.flight.services.FlightService;
import com.example.booking.hotel.models.Hotel;
import com.example.booking.hotel.services.HotelService;
import com.example.booking.room.models.Room;
import com.example.booking.room.services.RoomService;
import com.example.booking.seat.services.SeatService;
import com.example.booking.user.models.User;
import com.example.booking.user.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
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
  private RoomService roomService;
  private DaysService daysService;

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

  @Override
  public BookingHotelResponseDTO createHotelBooking(User user, BookingHotelRequestDTO bookingHotel) {
    Hotel hotel = hotelService.getHotelById(bookingHotel.getHotelId());
    List<Days> days = daysService.getFullTravelDates(bookingHotel.getStartDateInString(), bookingHotel.getEndDateInString()).stream()
            .map(date -> daysService.getByDate(date)).collect(Collectors.toList());

    List<Room> availableRooms = hotel.getRooms().stream()
            .filter(room -> roomService.isRoomAvailable(room, bookingHotel.getStartDateInString(), bookingHotel.getEndDateInString()))
            .collect(Collectors.toList());

    List<Room> oneRoomForEachRequestType = roomService.getOneRoomForEachGivenType(availableRooms, bookingHotel.getRooms());

    hotel.setRooms(roomService.setUnavailableDates(oneRoomForEachRequestType, days));

    Booking booking = bookingRepository.save(new Booking(bookingHotel.getStartDate(), bookingHotel.getEndDate(),
            bookingHotel.getTotalPrice(), user, hotel));

    return new BookingHotelResponseDTO(booking.getBookingDate(), booking.getStartDate(), booking.getEndDate(),
            booking.getTotalPrice(), hotelService.convertToResponseDTO(hotel));
  }

  @Override
  public BookingCarResponseDTO createCarBooking(User user, BookingCarRequestDTO bookingCar) {
    Car car = carService.getCarById(bookingCar.getCarId());

    List<Days> days = daysService.getFullTravelDates(bookingCar.getStartDateInString(),
                    bookingCar.getEndDateInString()).stream()
            .map(date -> daysService.getByDate(date)).collect(Collectors.toList());

    car.setUnavailable(days);

    Booking booking = bookingRepository.save(new Booking(bookingCar.getStartDate(), bookingCar.getEndDate(),
            bookingCar.getTotalPrice(), user, car));

    return new BookingCarResponseDTO(booking.getBookingDate(), booking.getStartDate(), booking.getEndDate(),
            booking.getTotalPrice(), carService.convertCarToCarDTO(
            car, days.size(), bookingCar.getStartDate(), bookingCar.getEndDate()));

  }

}
