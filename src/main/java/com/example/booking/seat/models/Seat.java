package com.example.booking.seat.models;

import com.example.booking.flight.models.Flight;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "seats")
@Entity
public class Seat {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @NotNull
  @Column(name = "seat_number")
  private String seatNumber;

  @NotNull
  @Column(name = "seat_type")
  private String seatType;

  @NotNull
  @Column(name = "price")
  private int price;

  @NotNull
  @Column(name = "availability")
  private Boolean availability;

  @JsonIgnore
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "flight_id", nullable = false)
  private Flight flight;

  public Seat(String seatNumber, String seatType, int price, Boolean availability) {
    this.seatNumber = seatNumber;
    this.seatType = seatType;
    this.price = price;
    this.availability = availability;
  }

}
