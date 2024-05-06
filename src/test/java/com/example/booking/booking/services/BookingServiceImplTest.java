package com.example.booking.booking.services;

import com.example.booking.booking.DTOs.AllBookingsResponseDTO;
import com.example.booking.booking.DTOs.bookingCar.BookingCarRequestDTO;
import com.example.booking.booking.DTOs.bookingCar.BookingCarResponseDTO;
import com.example.booking.booking.DTOs.bookingFlight.BookingOneWayFlightRequestDTO;
import com.example.booking.booking.DTOs.bookingFlight.BookingOneWayFlightResponseDTO;
import com.example.booking.booking.DTOs.bookingFlight.BookingRoundTripFlightRequestDTO;
import com.example.booking.booking.DTOs.bookingFlight.BookingRoundTripFlightResponseDTO;
import com.example.booking.booking.DTOs.bookingHotel.BookingHotelRequestDTO;
import com.example.booking.booking.DTOs.bookingHotel.BookingHotelResponseDTO;
import com.example.booking.booking.DTOs.bookingHotel.BookingRoomDTO;
import com.example.booking.booking.models.Booking;
import com.example.booking.booking.repositories.BookingRepository;
import com.example.booking.car.DTOs.CarDTO;
import com.example.booking.car.models.Car;
import com.example.booking.car.models.CarType;
import com.example.booking.car.models.TransmissionType;
import com.example.booking.car.services.CarService;
import com.example.booking.date.models.Days;
import com.example.booking.date.services.DaysService;
import com.example.booking.exceptions.IdNotFoundException;
import com.example.booking.exceptions.NoBookingFoundException;
import com.example.booking.flight.DTOs.FlightDTO;
import com.example.booking.flight.models.Flight;
import com.example.booking.flight.services.FlightService;
import com.example.booking.hotel.DTOs.HotelBookingResponseDTO;
import com.example.booking.hotel.models.Hotel;
import com.example.booking.hotel.services.HotelService;
import com.example.booking.room.models.Room;
import com.example.booking.room.models.RoomType;
import com.example.booking.room.services.RoomService;
import com.example.booking.seat.models.Seat;
import com.example.booking.seat.models.SeatType;
import com.example.booking.seat.services.SeatService;
import com.example.booking.user.models.User;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.*;

public class BookingServiceImplTest {

  private BookingService bookingService;

  @Mock
  private BookingRepository mockBookingRepository;
  @Mock
  private HotelService mockHotelService;
  @Mock
  private FlightService mockFlightService;
  @Mock
  private CarService mockCarService;
  @Mock
  private SeatService mockSeatService;
  @Mock
  private RoomService mockRoomService;
  @Mock
  private DaysService mockDaysService;

  private BookingCarRequestDTO requestCar;
  private BookingCarResponseDTO expectedCarBooking;
  private BookingCarResponseDTO receivedCarBooking;

  private BookingOneWayFlightRequestDTO requestOneWayFlight;
  private BookingOneWayFlightResponseDTO expectedOneWayFlightBooking;
  private BookingOneWayFlightResponseDTO receivedOneWayFlightBooking;

  private BookingRoundTripFlightRequestDTO requestRoundTripFlight;
  private BookingRoundTripFlightResponseDTO expectedRoundTripFlightBooking;
  private BookingRoundTripFlightResponseDTO receivedRoundTripFlightBooking;

  private BookingHotelRequestDTO requestHotel;
  private BookingHotelResponseDTO expectedHotelBooking;
  private BookingHotelResponseDTO receivedHotelBooking;

  private User user;

  private Flight oneWayFlight;
  private List<Seat> oneWayFlightSeats;

  private Flight outBoundFlight;
  private List<Seat> outBoundFlightSeats;
  private Flight returnFlight;
  private List<Seat> returnFlightSeats;

  private Car car;

  private Hotel hotel;
  private List<Room> rooms;

  private Booking savedBooking;

  @Before
  public void setup() {
    MockitoAnnotations.openMocks(this);
    this.bookingService = Mockito.spy(new BookingServiceImpl(mockBookingRepository, mockHotelService, mockFlightService,
            mockCarService, mockSeatService, mockRoomService, mockDaysService));
    expectedCarBooking = new BookingCarResponseDTO();
    expectedHotelBooking = new BookingHotelResponseDTO();
    expectedOneWayFlightBooking = new BookingOneWayFlightResponseDTO();
    expectedRoundTripFlightBooking = new BookingRoundTripFlightResponseDTO();
    user = new User(1, "", "", "Oli", "szabo.oliver2001@gmail.com",
            "Oli12345", "1234567", "", "", "", null, true,
            null, null, null);

    oneWayFlight = new Flight("Delta Airlines", "New York", "JFK", "Los Angeles",
            "LAS", new Date(), 360, "", "");
    oneWayFlightSeats = Arrays.asList((new Seat("1", SeatType.ECONOMY, 100, true)));

    outBoundFlight = new Flight("American Airlines", "Chicago", "ORD", "Miami",
            "MIA", new Date(), 180, "", "");
    outBoundFlightSeats = Arrays.asList((new Seat("3", SeatType.ECONOMY, 100, true)));
    returnFlight = new Flight("American Airlines", "Miami", "MIA", "Chicago",
            "ORD", new Date(), 180, "", "");
    returnFlightSeats = Arrays.asList((new Seat("65", SeatType.BUSINESS, 300, true)));

    car = new Car(1, "Toyota", "Camry", CarType.MEDIUM, 5, TransmissionType.AUTOMATIC,
            "New York", "New York", 50, "camry.png",
            new ArrayList<>(), new ArrayList<>());

    hotel = new Hotel("Hilton", "New York", "1234 Avenue of the Americas", 5, "hilton.png");
    rooms = new ArrayList<>();

  }

  @Test(expected = IdNotFoundException.class)
  public void testCreateOneWayFlightBookingWithFlightIdNotFound() {
    when(mockFlightService.getFlightById(oneWayFlight.getId())).thenThrow(IdNotFoundException.class);

    receivedOneWayFlightBooking = bookingService.createOneWayFlightBooking(user, new BookingOneWayFlightRequestDTO(
            null, 0, 0, new ArrayList<>()));

    verify(mockBookingRepository, times(0)).save(any(Booking.class));

  }

  @Test
  public void testCreateOneWayFlightBooking() {
    savedBooking = new Booking(new Date(), 1000, user, oneWayFlight, oneWayFlightSeats);
    FlightDTO flightDTO = new FlightDTO(1, "Delta Airlines", "New York", "JFK", "Los Angeles",
            "LAS", new Date(), 360, "", "", "",
            oneWayFlightSeats, new ArrayList<>());

    when(mockFlightService.getFlightById(oneWayFlight.getId())).thenReturn(oneWayFlight);
    when(mockSeatService.getSeatsById(oneWayFlightSeats.stream().map(Seat::getId)
            .collect(Collectors.toList()))).thenReturn(oneWayFlightSeats);
    oneWayFlightSeats.forEach(seat -> seat.setAvailability(false));
    when(mockSeatService.setSeatsAvailabilityFalse(oneWayFlightSeats)).thenReturn(oneWayFlightSeats);
    when(mockBookingRepository.save(any(Booking.class))).thenReturn(savedBooking);
    when(mockFlightService.convertToFlightDTO(oneWayFlight, oneWayFlightSeats)).thenReturn(flightDTO);

    requestOneWayFlight = new BookingOneWayFlightRequestDTO("2024-05-05 22:03:00", 1000,
            oneWayFlight.getId(), oneWayFlightSeats.stream().map(seat -> seat.getId()).collect(Collectors.toList()));

    expectedOneWayFlightBooking = new BookingOneWayFlightResponseDTO(new Date(), new Date(), 1000, flightDTO);

    receivedOneWayFlightBooking = bookingService.createOneWayFlightBooking(user, requestOneWayFlight);

    assertEquals(expectedOneWayFlightBooking.getFlight().getAirline(), receivedOneWayFlightBooking.getFlight().getAirline());
    assertEquals(expectedOneWayFlightBooking.getTotalPrice(), receivedOneWayFlightBooking.getTotalPrice());
    assertEquals(expectedOneWayFlightBooking.getFlight().getId(), receivedOneWayFlightBooking.getFlight().getId());
    receivedOneWayFlightBooking.getFlight().getSeats().forEach(seat -> assertFalse(seat.getAvailability()));
    verify(mockBookingRepository, times(1)).save(any(Booking.class));

  }

  @Test(expected = IdNotFoundException.class)
  public void testCreateRoundTripFlightBookingWithFlightIdNotFound() {
    when(mockFlightService.getFlightById(outBoundFlight.getId())).thenThrow(IdNotFoundException.class);
    when(mockFlightService.getFlightById(returnFlight.getId())).thenThrow(IdNotFoundException.class);

    receivedOneWayFlightBooking = bookingService.createOneWayFlightBooking(user, new BookingOneWayFlightRequestDTO(
            null, 0, 0, new ArrayList<>()));

    verify(mockBookingRepository, times(0)).save(any(Booking.class));

  }

  @Test
  public void testCreateRoundTripFlightBooking() {
    FlightDTO flight1 = new FlightDTO(2, "American Airlines", "Chicago", "ORD", "Miami",
            "MIA", new Date(), 180, "", "", "",
            oneWayFlightSeats, new ArrayList<>());
    FlightDTO flight2 = new FlightDTO(3, "American Airlines", "Miami", "MIA", "Chicago",
            "ORD", new Date(), 180, "", "", "",
            oneWayFlightSeats, new ArrayList<>());

    when(mockFlightService.getFlightById(outBoundFlight.getId())).thenReturn(oneWayFlight);
    when(mockFlightService.getFlightById(returnFlight.getId())).thenReturn(oneWayFlight);
    oneWayFlightSeats.forEach(seat -> seat.setAvailability(false));
    returnFlightSeats.forEach(seat -> seat.setAvailability(false));
    when(mockSeatService.setSeatsAvailabilityFalse(oneWayFlightSeats)).thenReturn(oneWayFlightSeats);
    when(mockSeatService.setSeatsAvailabilityFalse(returnFlightSeats)).thenReturn(returnFlightSeats);
    List<Seat> seats = new ArrayList<>(oneWayFlightSeats);
    seats.addAll(returnFlightSeats);
    savedBooking = new Booking(new Date(), new Date(), 1000, user, oneWayFlight, returnFlight, seats);
    when(mockBookingRepository.save(any(Booking.class))).thenReturn(savedBooking);
    when(mockFlightService.convertToFlightDTO(oneWayFlight, oneWayFlightSeats)).thenReturn(flight1);
    when(mockFlightService.convertToFlightDTO(returnFlight, returnFlightSeats)).thenReturn(flight2);

    requestRoundTripFlight = new BookingRoundTripFlightRequestDTO("2024-05-05 18:00:00", 2300,
            oneWayFlight.getId(), returnFlight.getId(), "2024-05-10 10:00:00", new ArrayList<>(), new ArrayList<>());

    expectedRoundTripFlightBooking = new BookingRoundTripFlightResponseDTO(new Date(), new Date(),
            2300, new Date(), flight1, flight2);

    receivedRoundTripFlightBooking = bookingService.createRoundTripFlightBooking(user, requestRoundTripFlight);

    assertEquals(expectedRoundTripFlightBooking.getFlightToDestination().getAirline(),
            expectedRoundTripFlightBooking.getFlightToDestination().getAirline());
    assertEquals(expectedRoundTripFlightBooking.getFlightReturn().getAirline(),
            expectedRoundTripFlightBooking.getFlightReturn().getAirline());
    assertEquals(expectedRoundTripFlightBooking.getTotalPrice(), expectedRoundTripFlightBooking.getTotalPrice());
    verify(mockBookingRepository, times(1)).save(any(Booking.class));

  }

  @Test(expected = IdNotFoundException.class)
  public void testCreateHotelBookingWithHotelIdNotFound() {
    when(mockHotelService.getHotelById(hotel.getId())).thenThrow(IdNotFoundException.class);

    receivedHotelBooking = bookingService.createHotelBooking(user,
            new BookingHotelRequestDTO("2024-05-05 22:03:00", 150, "2024-05-06 22:03:00",
                    hotel.getId(), new ArrayList<>()));

    verify(mockBookingRepository, times(0)).save(any(Booking.class));

  }

  @Test
  public void testHotelBooking() {
    rooms = new ArrayList<>(Arrays.asList(new Room(RoomType.SINGLE, 1, 50),
            new Room(RoomType.DOUBLE, 2, 100)));
    List<BookingRoomDTO> bookedRooms = new ArrayList<>(Arrays.asList(
            new BookingRoomDTO(RoomType.SINGLE, 1),
            new BookingRoomDTO(RoomType.DOUBLE, 1)));
    savedBooking = new Booking(new Date(), new Date(), 150, user, hotel, rooms);
    HotelBookingResponseDTO hotelDTO = new HotelBookingResponseDTO(1, "Hilton", "New York", "street",
            5, "hilton", new ArrayList<>(), new ArrayList<>());
    List<String> daysInString = new ArrayList<>(Arrays.asList("2024-05-05"));
    Days days = new Days(new Date());
    when(mockHotelService.getHotelById(hotel.getId())).thenReturn(hotel);
    when(mockDaysService.getFullTravelDates(anyString(), anyString())).thenReturn(daysInString);
    when(mockDaysService.getByDate(any(String.class))).thenReturn(days);
    when(mockRoomService.isRoomAvailable(any(Room.class), anyString(), anyString())).thenReturn(true);
    when(mockRoomService.getOneRoomForEachGivenType(anyList(), anyList())).thenReturn(rooms);
    when(mockBookingRepository.save(any(Booking.class))).thenReturn(savedBooking);
    when(mockHotelService.convertToHotelBookingResponseDTO(hotel, rooms)).thenReturn(hotelDTO);

    requestHotel = new BookingHotelRequestDTO("2024-05-05 22:03:00", 150, "2024-05-06 22:03:00",
            hotel.getId(), bookedRooms);

    expectedHotelBooking = new BookingHotelResponseDTO(new Date(), new Date(), 150, new Date(), hotelDTO);

    receivedHotelBooking = bookingService.createHotelBooking(user, requestHotel);

    assertEquals(expectedHotelBooking.getHotel(), receivedHotelBooking.getHotel());
    assertEquals(expectedHotelBooking.getTotalPrice(), receivedHotelBooking.getTotalPrice());
    verify(mockBookingRepository, times(1)).save(any(Booking.class));

  }

  @Test(expected = IdNotFoundException.class)
  public void testCreateCarBookingWithCarIdNotFound() {
    when(mockCarService.getCarById(car.getId())).thenThrow(IdNotFoundException.class);

    bookingService.createCarBooking(user,
            new BookingCarRequestDTO("2024-05-05 22:03:00", 150, "2024-05-06 22:03:00",
                    car.getId()));

    verify(mockBookingRepository, times(0)).save(any(Booking.class));

  }

  @Test
  public void testCarBooking() {
    savedBooking = new Booking(new Date(), new Date(), 50, user, car);
    CarDTO carDTO = new CarDTO(1, "Toyota", "Camry", CarType.MEDIUM, 5,
            TransmissionType.AUTOMATIC, "New York", "New York", new Date(), new Date(),
            50, "Camry.png", new ArrayList<>());
    List<String> daysInString = new ArrayList<>(Arrays.asList("2024-05-06"));
    Days days = new Days(new Date());
    when(mockCarService.getCarById(car.getId())).thenReturn(car);
    when(mockDaysService.getFullTravelDates(any(Date.class), any(Date.class))).thenReturn(daysInString);
    when(mockDaysService.getByDate(any(String.class))).thenReturn(days);
    when(mockBookingRepository.save(any(Booking.class))).thenReturn(savedBooking);
    when(mockCarService.convertCarToCarDTO(car, 1, new Date(), new Date())).thenReturn(carDTO);

    requestCar = new BookingCarRequestDTO("2024-06-05 10:00:00", 50, "2024-06-10 10:00:00",
            car.getId());

    receivedCarBooking = bookingService.createCarBooking(user, requestCar);

    verify(mockBookingRepository, times(1)).save(any(Booking.class));

  }

  @Test(expected = NoBookingFoundException.class)
  public void testGetBookingsWithNoBookingFound() {
    when(mockBookingRepository.findAllByUserId(user.getId())).thenThrow(NoBookingFoundException.class);

    bookingService.getBookings(user);
  }

  @Test
  public void testGetBookings() {
    List<Seat> seats = new ArrayList<>(oneWayFlightSeats);
    seats.addAll(returnFlightSeats);
    List<Booking> bookings = new ArrayList<>(Arrays.asList(
            new Booking(new Date(), new Date(), 50, user, car),
            new Booking(new Date(), new Date(), 150, user, hotel, rooms),
            new Booking(new Date(), new Date(), 1000, user, oneWayFlight, returnFlight, seats),
            new Booking(new Date(), 1000, user, oneWayFlight, oneWayFlightSeats)

    ));
    FlightDTO flightDTO = new FlightDTO(1, "Delta Airlines", "New York", "JFK", "Los Angeles",
            "LAS", new Date(), 360, "", "", "",
            oneWayFlightSeats, new ArrayList<>());

    FlightDTO flight1 = new FlightDTO(2, "American Airlines", "Chicago", "ORD", "Miami",
            "MIA", new Date(), 180, "", "", "",
            oneWayFlightSeats, new ArrayList<>());
    FlightDTO flight2 = new FlightDTO(3, "American Airlines", "Miami", "MIA", "Chicago",
            "ORD", new Date(), 180, "", "", "",
            oneWayFlightSeats, new ArrayList<>());

    HotelBookingResponseDTO hotelDTO = new HotelBookingResponseDTO(1, "Hilton", "New York", "street",
            5, "hilton", new ArrayList<>(), new ArrayList<>());

    CarDTO carDTO = new CarDTO(1, "Toyota", "Camry", CarType.MEDIUM, 5,
            TransmissionType.AUTOMATIC, "New York", "New York", new Date(), new Date(),
            50, "Camry.png", new ArrayList<>());


    when(mockBookingRepository.findAllByUserId(user.getId())).thenReturn(bookings);

    when(mockFlightService.convertToFlightDTO(oneWayFlight, oneWayFlightSeats)).thenReturn(flightDTO);
    when(mockFlightService.convertToFlightDTO(outBoundFlight, outBoundFlightSeats)).thenReturn(flight1);
    when(mockFlightService.convertToFlightDTO(returnFlight, returnFlightSeats)).thenReturn(flight2);
    when(mockHotelService.convertToHotelBookingResponseDTO(hotel, rooms)).thenReturn(hotelDTO);
    when(mockCarService.convertCarToCarDTO(car, 2, new Date(), new Date())).thenReturn(carDTO);

    AllBookingsResponseDTO receivedAllBookingsResponseDTO = bookingService.getBookings(user);

    assertEquals(receivedAllBookingsResponseDTO.getBookings().size(), 4);
  }

}
