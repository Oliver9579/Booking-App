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

  @OneToMany(mappedBy = "booking")
  private List<Room> rooms = new ArrayList<>();

  @OneToMany(mappedBy = "booking")
  private List<Seat> seats = new ArrayList<>();

  public Booking(User user, Date startDate, Date endDate, int totalPrice) {
    this.user = user;
    this.bookingDate = new Date();
    this.startDate = startDate;
    this.endDate = endDate;
    this.totalPrice = totalPrice;
  }

  public Booking(Date startDate, int totalPrice, User user,
                 Flight outboundFlight) {
    this.bookingDate = new Date();
    this.startDate = startDate;
    this.totalPrice = totalPrice;
    this.user = user;
    this.outboundFlight = outboundFlight;
  }

  public Booking(Date startDate, Date endDate, int totalPrice, User user, Flight outboundFlight,
                 Flight returnFlight) {
    this.bookingDate = new Date();
    this.startDate = startDate;
    this.endDate = endDate;
    this.totalPrice = totalPrice;
    this.user = user;
    this.outboundFlight = outboundFlight;
    this.returnFlight = returnFlight;
  }

  public Booking(Date startDate, Date endDate, int totalPrice, User user, Hotel hotel) {
    this.bookingDate = new Date();
    this.startDate = startDate;
    this.endDate = endDate;
    this.totalPrice = totalPrice;
    this.user = user;
    this.hotel = hotel;
  }

  public Booking(Date startDate, Date endDate, int totalPrice, User user, Car car) {
    this.bookingDate = new Date();
    this.startDate = startDate;
    this.endDate = endDate;
    this.totalPrice = totalPrice;
    this.user = user;
    this.car = car;
  }
}
