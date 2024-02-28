package com.example.booking.room.models;

import com.example.booking.booking.models.Booking;
import com.example.booking.date.models.Days;
import com.example.booking.hotel.models.Hotel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "rooms")
public class Room {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @NotNull
  @Column(name = "room_type")
  @Enumerated(EnumType.STRING)
  private RoomType roomType;

  @NotNull
  @Column(name = "capacity")
  private int capacity;

  @NotNull
  @Column(name = "price_per_night")
  private int pricePerNight;

  @JsonIgnore
  @ManyToMany(mappedBy = "rooms", cascade = CascadeType.ALL)
  private List<Days> unavailable;

  @JsonIgnore
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "hotel_id", nullable = false)
  private Hotel hotel;

  public Room(RoomType roomType, int capacity, int pricePerNight) {
    this.roomType = roomType;
    this.capacity = capacity;
    this.pricePerNight = pricePerNight;
  }

}
