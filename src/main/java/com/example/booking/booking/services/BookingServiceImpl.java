package com.example.booking.booking.services;

import com.example.booking.booking.DTOs.AllBookingsResponseDTO;
import com.example.booking.booking.DTOs.BookingResponseDTO;
import com.example.booking.booking.DTOs.bookingCar.BookingCarRequestDTO;
import com.example.booking.booking.DTOs.bookingCar.BookingCarResponseDTO;
import com.example.booking.booking.DTOs.bookingFlight.BookingOneWayFlightRequestDTO;
import com.example.booking.booking.DTOs.bookingFlight.BookingOneWayFlightResponseDTO;
import com.example.booking.booking.DTOs.bookingFlight.BookingRoundTripFlightRequestDTO;
import com.example.booking.booking.DTOs.bookingFlight.BookingRoundTripFlightResponseDTO;
import com.example.booking.booking.DTOs.bookingHotel.BookingHotelRequestDTO;
import com.example.booking.booking.DTOs.bookingHotel.BookingHotelResponseDTO;
import com.example.booking.booking.models.Booking;
import com.example.booking.booking.repositories.BookingRepository;
import com.example.booking.car.models.Car;
import com.example.booking.car.services.CarService;
import com.example.booking.date.models.Days;
import com.example.booking.date.services.DaysService;
import com.example.booking.exceptions.NoBookingFoundException;
import com.example.booking.flight.models.Flight;
import com.example.booking.flight.services.FlightService;
import com.example.booking.hotel.models.Hotel;
import com.example.booking.hotel.services.HotelService;
import com.example.booking.room.models.Room;
import com.example.booking.room.services.RoomService;
import com.example.booking.seat.models.Seat;
import com.example.booking.seat.services.SeatService;
import com.example.booking.user.models.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookingServiceImpl implements BookingService {

  private BookingRepository bookingRepository;
  private HotelService hotelService;
  private FlightService flightService;
  private CarService carService;
  private SeatService seatService;
  private RoomService roomService;
  private DaysService daysService;

  @Override
  public BookingOneWayFlightResponseDTO createOneWayFlightBooking(User user, BookingOneWayFlightRequestDTO bookingFlight) {
    Flight flight = flightService.getFlightById(bookingFlight.getOutboundFlightId());
    List<Seat> seats = seatService.setSeatsAvailabilityFalse(seatService.getSeatsById(bookingFlight.getSeatsId()));

    Booking booking = bookingRepository.save(new Booking(bookingFlight.getStartDate(),
            bookingFlight.getTotalPrice(), user, flight, seats));

    return new BookingOneWayFlightResponseDTO(booking.getBookingDate(), booking.getStartDate(), booking.getTotalPrice(),
            flightService.convertToFlightDTO(booking.getOutboundFlight(), booking.getBookedSeats()));
  }

  @Override
  public BookingRoundTripFlightResponseDTO createRoundTripFlightBooking(
          User user, BookingRoundTripFlightRequestDTO bookingFlight) {
    Flight flightToDestination = flightService.getFlightById(bookingFlight.getOutboundFlightId());
    Flight flightReturn = flightService.getFlightById(bookingFlight.getReturnFlightId());
    List<Seat> seatsForFlightOne = seatService.setSeatsAvailabilityFalse(
            seatService.getSeatsById(bookingFlight.getOutboundFlightSeatsId()));
    List<Seat> seatsForFlightTwo = seatService.setSeatsAvailabilityFalse(
            seatService.getSeatsById(bookingFlight.getReturnFlightSeatsId()));

    List<Seat> seats = new ArrayList<>(seatsForFlightOne);
    seats.addAll(seatsForFlightTwo);

    Booking booking = bookingRepository.save(new Booking(bookingFlight.getStartDate(), bookingFlight.getEndDate(),
            bookingFlight.getTotalPrice(), user, flightToDestination, flightReturn, seats));

    return new BookingRoundTripFlightResponseDTO(
            booking.getBookingDate(), booking.getStartDate(), booking.getTotalPrice(), booking.getEndDate(),
            flightService.convertToFlightDTO(booking.getOutboundFlight(), booking.getBookedSeats().stream()
                    .filter(seat -> seat.getFlight() == booking.getOutboundFlight()).collect(Collectors.toList())),
            flightService.convertToFlightDTO(booking.getReturnFlight(), booking.getBookedSeats().stream()
                    .filter(seat -> seat.getFlight() == booking.getReturnFlight()).collect(Collectors.toList())));
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

    roomService.setUnavailableDates(oneRoomForEachRequestType, days);

    Booking booking = bookingRepository.save(new Booking(bookingHotel.getStartDate(), bookingHotel.getEndDate(),
            bookingHotel.getTotalPrice(), user, hotel, oneRoomForEachRequestType));

    return new BookingHotelResponseDTO(booking.getBookingDate(), booking.getStartDate(), booking.getTotalPrice(),
            booking.getEndDate(), hotelService.convertToHotelBookingResponseDTO(hotel, booking.getBookedRooms()));
  }

  @Override
  public BookingCarResponseDTO createCarBooking(User user, BookingCarRequestDTO bookingCar) {
    Car car = carService.getCarById(bookingCar.getCarId());

    List<Days> days = daysService.getFullTravelDates(bookingCar.getStartDateInString(),
                    bookingCar.getEndDateInString()).stream()
            .map(date -> daysService.getByDate(date)).collect(Collectors.toList());

    days.addAll(car.getUnavailable());
    car.setUnavailable(days);

    Booking booking = bookingRepository.save(new Booking(bookingCar.getStartDate(), bookingCar.getEndDate(),
            bookingCar.getTotalPrice(), user, car));

    return new BookingCarResponseDTO(booking.getBookingDate(), booking.getStartDate(), booking.getTotalPrice(),
            booking.getEndDate(), carService.convertCarToCarDTO(
            car, days.size(), bookingCar.getStartDate(), bookingCar.getEndDate()));

  }

  @Override
  public AllBookingsResponseDTO getBookings(User user) {
    List<Booking> bookings = bookingRepository.findAllByUserId(user.getId());
    if (bookings.isEmpty()) throw new NoBookingFoundException();
    List<BookingResponseDTO> bookingsResponse = new ArrayList<>();
    for (Booking booking : bookings) {
      if (booking.getOutboundFlight() != null && booking.getReturnFlight() == null) {
        bookingsResponse.add(new BookingOneWayFlightResponseDTO(booking.getBookingDate(),
                booking.getStartDate(), booking.getTotalPrice(),
                flightService.convertToFlightDTO(booking.getOutboundFlight(), booking.getBookedSeats())));
      } else if (booking.getOutboundFlight() != null && booking.getReturnFlight() != null) {
        bookingsResponse.add(new BookingRoundTripFlightResponseDTO(
                booking.getBookingDate(), booking.getStartDate(), booking.getTotalPrice(), booking.getEndDate(),
                flightService.convertToFlightDTO(booking.getOutboundFlight(), booking.getBookedSeats().stream()
                        .filter(seat -> seat.getFlight() == booking.getOutboundFlight()).collect(Collectors.toList())),
                flightService.convertToFlightDTO(booking.getReturnFlight(), booking.getBookedSeats().stream()
                        .filter(seat -> seat.getFlight() == booking.getReturnFlight()).collect(Collectors.toList()))));
      } else if (booking.getCar() != null) {
        bookingsResponse.add(new BookingCarResponseDTO(booking.getBookingDate(), booking.getStartDate(), booking.getTotalPrice(),
                booking.getEndDate(), carService.convertCarToCarDTO(
                booking.getCar(), 0, booking.getStartDate(), booking.getEndDate())));
      } else if (booking.getHotel() != null) {
        bookingsResponse.add(new BookingHotelResponseDTO(booking.getBookingDate(), booking.getStartDate(), booking.getTotalPrice(),
                booking.getEndDate(), hotelService.convertToHotelBookingResponseDTO(booking.getHotel(), booking.getBookedRooms())));
      }
    }
    return new AllBookingsResponseDTO(bookingsResponse);
  }

}
