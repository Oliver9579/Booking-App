package com.example.booking.review.models;

import com.example.booking.car.models.Car;
import com.example.booking.flight.models.Flight;
import com.example.booking.hotel.models.Hotel;
import com.example.booking.user.models.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "reviews")
public class Review {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "comment", length = 2000, nullable = false)
  private String comment;

  @Column(name = "review_date", nullable = false)
  private Date reviewDate;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "flight_id")
  private Flight flight;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "hotel_id")
  private Hotel hotel;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "car_id")
  private Car car;

  public Review(String comment, User user, Flight flight, Hotel hotel, Car car) {
    this.comment = comment;
    this.reviewDate = new Date();
    this.user = user;
    this.flight = flight;
    this.hotel = hotel;
    this.car = car;
  }

  public Review(String comment, User user, Car car) {
    this.comment = comment;
    this.reviewDate = new Date();
    this.user = user;
    this.car = car;
  }

  public Review(String comment, User user, Flight flight) {
    this.comment = comment;
    this.reviewDate = new Date();
    this.user = user;
    this.flight = flight;
  }

  public Review(String comment, User user, Hotel hotel) {
    this.comment = comment;
    this.reviewDate = new Date();
    this.user = user;
    this.hotel = hotel;
  }
}
