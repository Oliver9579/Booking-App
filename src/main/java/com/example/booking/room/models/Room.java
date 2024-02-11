package com.example.booking.room.models;

import com.example.booking.hotel.models.Hotel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

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
  private RoomType room;

  @NotNull
  @Column(name = "capacity")
  private int capacity;

  @NotNull
  @Column(name = "price_per_night")
  private int pricePerNight;

  @NotNull
  @Column(name = "availability")
  private Boolean availability;

  @JsonIgnore
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "hotel_id", nullable = false)
  private Hotel hotel;

  public Room(RoomType room, int capacity, int pricePerNight, Boolean availability) {
    this.room = room;
    this.capacity = capacity;
    this.pricePerNight = pricePerNight;
    this.availability = availability;
  }

}
