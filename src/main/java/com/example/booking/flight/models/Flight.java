package com.example.booking.flight.models;

import com.example.booking.booking.models.Booking;
import com.example.booking.review.models.Review;
import com.example.booking.seat.models.Seat;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "flights")
@Entity
public class Flight {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @NotNull
  @Column(name = "airline")
  private String airline;

  @NotNull
  @Column(name = "origin")
  private String origin;

  @NotNull
  @Column(name = "destination")
  private String destination;

  @NotNull
  @Column(name = "departure_date")
  private Date departureDate;

  @NotNull
  @Column(name = "duration")
  private int duration;

  @NotNull
  @Column(name = "created_at")
  private Long createdAt;

  @OneToMany(mappedBy = "flight", cascade = CascadeType.ALL)
  @JsonIgnore
  private List<Seat> seats = new ArrayList<>();

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
  private List<Booking> bookings = new ArrayList<>();

  @OneToMany(mappedBy = "flight", cascade = CascadeType.ALL)
  private List<Review> reviews = new ArrayList<>();

  public Flight(String airline, String origin, String destination, Date departureDate, int duration) {
    this.airline = airline;
    this.origin = origin;
    this.destination = destination;
    this.departureDate = departureDate;
    this.duration = duration;
    this.createdAt = System.currentTimeMillis() / 1000;
  }

}
