package com.example.booking.booking.controllers;

import com.example.booking.booking.DTOs.bookingCar.BookingCarRequestDTO;
import com.example.booking.booking.DTOs.bookingFlight.BookingOneWayFlightRequestDTO;
import com.example.booking.booking.DTOs.bookingFlight.BookingRoundTripFlightRequestDTO;
import com.example.booking.booking.DTOs.bookingHotel.BookingHotelRequestDTO;
import com.example.booking.booking.DTOs.bookingHotel.BookingRoomDTO;
import com.example.booking.room.models.RoomType;
import com.example.booking.user.models.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@Sql("/data.sql")
public class BookingControllerIT {

  @Autowired
  private MockMvc mockMvc;
  private final MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
          MediaType.APPLICATION_JSON.getSubtype(),
          StandardCharsets.UTF_8);
  private ObjectMapper mapper;
  private User user1;
  private User user2;
  private Authentication userAuth1;
  private Authentication userAuth2;

  @Before
  public void setup() {
    mapper = new ObjectMapper();
    user1 = new User(100, "", "", "Oli", "booking.2024.test@gmail.com",
            "Oli12345", "123456789");
    user2 = new User(0, "", "", "", "",
            "", "");
    userAuth1 = new UsernamePasswordAuthenticationToken(user1, null, null);
    userAuth2 = new UsernamePasswordAuthenticationToken(user2, null, null);
  }

  @Test
  public void createOneWayNewFlightBooking_should_ReturnError_when_UserIdNotFound() throws Exception {
    BookingOneWayFlightRequestDTO newOneWayFlightBookingRequest = new BookingOneWayFlightRequestDTO(
            "2024-06-15 16:30:00", 840, 1, Arrays.asList(1, 2));
    mockMvc.perform(post("/api/bookings/flights/oneWay")
                    .principal(userAuth2)
                    .contentType(contentType)
                    .content(mapper.writeValueAsString(newOneWayFlightBookingRequest)))
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(status().isUnauthorized())
            .andExpect(jsonPath("$.status", Matchers.is("error")))
            .andExpect(jsonPath("$.message", Matchers.is("No such user.")));
  }

  @Test
  public void createOneWayNewFlightBooking_should_ReturnError_when_FlightIdNotFound() throws Exception {
    BookingOneWayFlightRequestDTO newOneWayFlightBookingRequest = new BookingOneWayFlightRequestDTO(
            "2024-06-15 16:30:00", 840, 0, Arrays.asList(1, 2));
    mockMvc.perform(post("/api/bookings/flights/oneWay")
                    .principal(userAuth1)
                    .contentType(contentType)
                    .content(mapper.writeValueAsString(newOneWayFlightBookingRequest)))
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound())
            .andExpect(jsonPath("$.status", Matchers.is("error")))
            .andExpect(jsonPath("$.message", Matchers.is("Id not found")));
  }

  @Test
  public void createOneWayNewFlightBooking_should_ReturnError_when_SeatIdNotFound() throws Exception {
    BookingOneWayFlightRequestDTO newOneWayFlightBookingRequest = new BookingOneWayFlightRequestDTO(
            "2024-06-15 16:30:00", 840, 1, Arrays.asList(0, 2));
    mockMvc.perform(post("/api/bookings/flights/oneWay")
                    .principal(userAuth1)
                    .contentType(contentType)
                    .content(mapper.writeValueAsString(newOneWayFlightBookingRequest)))
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound())
            .andExpect(jsonPath("$.status", Matchers.is("error")))
            .andExpect(jsonPath("$.message", Matchers.is("Id not found")));
  }

  @Test
  public void createOneWayNewFlightBooking_should_ReturnTheSavedBooking() throws Exception {
    BookingOneWayFlightRequestDTO newOneWayFlightBookingRequest = new BookingOneWayFlightRequestDTO(
            "2024-06-15 16:30:00", 840, 1, Arrays.asList(1, 2));
    mockMvc.perform(post("/api/bookings/flights/oneWay")
                    .principal(userAuth1)
                    .contentType(contentType)
                    .content(mapper.writeValueAsString(newOneWayFlightBookingRequest)))
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.totalPrice").value(840))
            .andExpect(jsonPath("$.flight.id").value(1))
            .andExpect(jsonPath("$.flight.airline").value("Delta Airlines"))
            .andExpect(jsonPath("$.flight.origin").value("New York"))
            .andExpect(jsonPath("$.flight.destination").value("Los Angeles"))
            .andExpect(jsonPath("$.flight.seats.length()").value(2));
  }

  @Test
  public void createRoundTripNewFlightBooking_should_ReturnError_when_UserIdNotFound() throws Exception {
    BookingRoundTripFlightRequestDTO newRoundTripFlightBookingRequest = new BookingRoundTripFlightRequestDTO(
            "2024-06-20 00:00:00", 669, 2, 3, "2024-06-28 20:00:00",
            List.of(9), List.of(16));
    mockMvc.perform(post("/api/bookings/flights/roundTrip")
                    .principal(userAuth2)
                    .contentType(contentType)
                    .content(mapper.writeValueAsString(newRoundTripFlightBookingRequest)))
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(status().isUnauthorized())
            .andExpect(jsonPath("$.status", Matchers.is("error")))
            .andExpect(jsonPath("$.message", Matchers.is("No such user.")));
  }

  @Test
  public void createRoundTripNewFlightBooking_should_ReturnError_when_OneOfTheFlightIdNotFound() throws Exception {
    BookingRoundTripFlightRequestDTO newRoundTripFlightBookingRequest = new BookingRoundTripFlightRequestDTO(
            "2024-06-20 00:00:00", 669, 0, 3, "2024-06-28 20:00:00",
            List.of(9), List.of(16));
    mockMvc.perform(post("/api/bookings/flights/roundTrip")
                    .principal(userAuth1)
                    .contentType(contentType)
                    .content(mapper.writeValueAsString(newRoundTripFlightBookingRequest)))
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound())
            .andExpect(jsonPath("$.status", Matchers.is("error")))
            .andExpect(jsonPath("$.message", Matchers.is("Id not found")));
  }

  @Test
  public void createRoundTripNewFlightBooking_should_ReturnError_when_SeatIdNotFound() throws Exception {
    BookingRoundTripFlightRequestDTO newRoundTripFlightBookingRequest = new BookingRoundTripFlightRequestDTO(
            "2024-06-20 00:00:00", 669, 2, 3, "2024-06-28 20:00:00",
            List.of(0), List.of(16));
    mockMvc.perform(post("/api/bookings/flights/roundTrip")
                    .principal(userAuth1)
                    .contentType(contentType)
                    .content(mapper.writeValueAsString(newRoundTripFlightBookingRequest)))
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound())
            .andExpect(jsonPath("$.status", Matchers.is("error")))
            .andExpect(jsonPath("$.message", Matchers.is("Id not found")));
  }

  @Test
  public void createRoundTripNewFlightBooking_should_ReturnTheSavedFlight() throws Exception {
    BookingRoundTripFlightRequestDTO newRoundTripFlightBookingRequest = new BookingRoundTripFlightRequestDTO(
            "2024-06-20 00:00:00", 669, 2, 3, "2024-06-28 20:00:00",
            List.of(9), List.of(16));
    mockMvc.perform(post("/api/bookings/flights/roundTrip")
                    .principal(userAuth1)
                    .contentType(contentType)
                    .content(mapper.writeValueAsString(newRoundTripFlightBookingRequest)))
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.totalPrice").value(669))
            .andExpect(jsonPath("$.flightToDestination.id").value(2))
            .andExpect(jsonPath("$.flightReturn.id").value(3))
            .andExpect(jsonPath("$.flightToDestination.airline").value("American Airlines"))
            .andExpect(jsonPath("$.flightReturn.airline").value("American Airlines"))
            .andExpect(jsonPath("$.flightToDestination.origin").value("Chicago"))
            .andExpect(jsonPath("$.flightReturn.origin").value("Miami"))
            .andExpect(jsonPath("$.flightToDestination.destination").value("Miami"))
            .andExpect(jsonPath("$.flightReturn.destination").value("Chicago"))
            .andExpect(jsonPath("$.flightToDestination.seats.length()").value(1))
            .andExpect(jsonPath("$.flightReturn.seats.length()").value(1));
  }

  @Test
  public void createHotelBooking_should_ReturnError_when_UserIdNotFound() throws Exception {
    BookingHotelRequestDTO newHotelBookingRequest = new BookingHotelRequestDTO(
            "2024-06-12 14:00:00", 700, "2024-06-14 10:00:00", 1, Arrays.asList(
            new BookingRoomDTO(RoomType.SINGLE, 1), new BookingRoomDTO(RoomType.FAMILY, 1)));
    mockMvc.perform(post("/api/bookings/hotels")
                    .principal(userAuth2)
                    .contentType(contentType)
                    .content(mapper.writeValueAsString(newHotelBookingRequest)))
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(status().isUnauthorized())
            .andExpect(jsonPath("$.status", Matchers.is("error")))
            .andExpect(jsonPath("$.message", Matchers.is("No such user.")));
  }

  @Test
  public void createHotelBooking_should_ReturnError_when_HotelIdNotFound() throws Exception {
    BookingHotelRequestDTO newHotelBookingRequest = new BookingHotelRequestDTO(
            "2024-06-12 14:00:00", 700, "2024-06-14 10:00:00", 0, Arrays.asList(
            new BookingRoomDTO(RoomType.SINGLE, 1), new BookingRoomDTO(RoomType.FAMILY, 1)));
    mockMvc.perform(post("/api/bookings/hotels")
                    .principal(userAuth1)
                    .contentType(contentType)
                    .content(mapper.writeValueAsString(newHotelBookingRequest)))
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound())
            .andExpect(jsonPath("$.status", Matchers.is("error")))
            .andExpect(jsonPath("$.message", Matchers.is("Id not found")));
  }

  @Test
  public void createHotelBooking_should_ReturnTheSavedBooking() throws Exception {
    BookingHotelRequestDTO newHotelBookingRequest = new BookingHotelRequestDTO(
            "2024-06-12 14:00:00", 800, "2024-06-14 10:00:00", 1, Arrays.asList(
            new BookingRoomDTO(RoomType.DOUBLE, 1), new BookingRoomDTO(RoomType.FAMILY, 1)));
    mockMvc.perform(post("/api/bookings/hotels")
                    .principal(userAuth1)
                    .contentType(contentType)
                    .content(mapper.writeValueAsString(newHotelBookingRequest)))
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.totalPrice").value(800))
            .andExpect(jsonPath("$.hotel.id").value(1))
            .andExpect(jsonPath("$.hotel.name").value("Hilton"))
            .andExpect(jsonPath("$.hotel.location").value("New York"))
            .andExpect(jsonPath("$.hotel.street").value("1234 Avenue of the Americas"))
            .andExpect(jsonPath("$.hotel.stars").value(5))
            .andExpect(jsonPath("$.hotel.rooms.length()").value(2));
  }

  @Test
  public void createCarBooking_should_ReturnError_when_UserIdNotFound() throws Exception {
    BookingCarRequestDTO newCarBookingRequest = new BookingCarRequestDTO(
            "2024-06-06 10:00:00", 300, "2024-06-08 10:00:00", 2);
    mockMvc.perform(post("/api/bookings/cars")
                    .principal(userAuth2)
                    .contentType(contentType)
                    .content(mapper.writeValueAsString(newCarBookingRequest)))
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(status().isUnauthorized())
            .andExpect(jsonPath("$.status", Matchers.is("error")))
            .andExpect(jsonPath("$.message", Matchers.is("No such user.")));
  }

  @Test
  public void createCarBooking_should_ReturnError_when_CarIdNotFound() throws Exception {
    BookingCarRequestDTO newCarBookingRequest = new BookingCarRequestDTO(
            "2024-06-06 10:00:00", 300, "2024-06-08 10:00:00", 0);
    mockMvc.perform(post("/api/bookings/cars")
                    .principal(userAuth1)
                    .contentType(contentType)
                    .content(mapper.writeValueAsString(newCarBookingRequest)))
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound())
            .andExpect(jsonPath("$.status", Matchers.is("error")))
            .andExpect(jsonPath("$.message", Matchers.is("Id not found")));
  }

  @Test
  public void createCarBooking_should_ReturnTheSavedBooking() throws Exception {
    BookingCarRequestDTO newCarBookingRequest = new BookingCarRequestDTO(
            "2024-06-06 10:00:00", 300, "2024-06-08 10:00:00", 2);
    mockMvc.perform(post("/api/bookings/cars")
                    .principal(userAuth1)
                    .contentType(contentType)
                    .content(mapper.writeValueAsString(newCarBookingRequest)))
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.totalPrice").value(300))
            .andExpect(jsonPath("$.car.id").value(2))
            .andExpect(jsonPath("$.car.brand").value("Ford"))
            .andExpect(jsonPath("$.car.model").value("Mustang"))
            .andExpect(jsonPath("$.car.carType").value("MEDIUM"))
            .andExpect(jsonPath("$.car.capacity").value(5))
            .andExpect(jsonPath("$.car.transmissionType").value("MANUAL"))
            .andExpect(jsonPath("$.car.pickUpLocation").value("Chicago"))
            .andExpect(jsonPath("$.car.dropOffLocation").value("Miami"));
  }

}
