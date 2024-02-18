package com.example.booking.hotel.models;

import com.example.booking.room.models.Room;
import com.example.booking.seat.models.Seat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "hotels")
public class Hotel {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @NotNull
  @Column(name = "name")
  private String name;

  @NotNull
  @Column(name = "location")
  private String location;

  @NotNull
  @Column(name = "street")
  private String street;

  @NotNull
  @Column(name = "created_at")
  private Long createdAt;

  @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL)
  @JsonIgnore
  private List<Room> rooms = new ArrayList<>();

  public Hotel(String name, String location, Long createdAt) {
    this.name = name;
    this.location = location;
    this.createdAt = createdAt;
    this.createdAt = System.currentTimeMillis() / 1000;
  }

}
