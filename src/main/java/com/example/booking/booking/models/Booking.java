package com.example.booking.booking.models;

import com.example.booking.car.models.Car;
import com.example.booking.flight.models.Flight;
import com.example.booking.hotel.models.Hotel;
import com.example.booking.user.models.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

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
  @JoinColumn(name = "flight_id")
  private Flight flight;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "hotel_id")
  private Hotel hotel;

  public Booking(User user, Date bookingDate, Date startDate, Date endDate, int totalPrice) {
    this.user = user;
    this.bookingDate = bookingDate;
    this.startDate = startDate;
    this.endDate = endDate;
    this.totalPrice = totalPrice;
  }

}
