package com.example.booking.booking.models;

import com.example.booking.car.models.Car;
import com.example.booking.flight.models.Flight;
import com.example.booking.hotel.models.Hotel;
import com.example.booking.room.models.Room;
import com.example.booking.seat.models.Seat;
import com.example.booking.user.models.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bookings")
public class Booking {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @NotNull
  @Column(name = "booking_date")
  private Date bookingDate;

  @NotNull
  @Column(name = "start_date")
  private Date startDate;

  @Column(name = "end_date")
  private Date endDate;

  @NotNull
  @Column(name = "total_price")
  private int totalPrice;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "car_id")
  private Car car;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "outbound_flight_id")
  private Flight outboundFlight;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "return_flight_id")
  private Flight returnFlight;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "hotel_id")
  private Hotel hotel;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(
          name = "booking_rooms",
          joinColumns = @JoinColumn(name = "booking_id"),
          inverseJoinColumns = @JoinColumn(name = "room_id")
  )
  private List<Room> bookedRooms = new ArrayList<>();

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(
          name = "booking_seats",
          joinColumns = @JoinColumn(name = "booking_id"),
          inverseJoinColumns = @JoinColumn(name = "seat_id")
  )
  private List<Seat> bookedSeats = new ArrayList<>();

  public Booking(User user, Date startDate, Date endDate, int totalPrice) {
    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    this.user = user;
    this.bookingDate = new Date();
    this.startDate = startDate;
    this.endDate = endDate;
    this.totalPrice = totalPrice;
  }

  public Booking(Date startDate, int totalPrice, User user,
                 Flight outboundFlight, List<Seat> bookedSeats) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    this.bookingDate = new Date();
    this.startDate = startDate;
    this.totalPrice = totalPrice;
    this.user = user;
    this.outboundFlight = outboundFlight;
    this.bookedSeats = bookedSeats;
  }
}
