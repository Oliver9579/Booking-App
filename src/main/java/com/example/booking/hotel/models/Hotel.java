package com.example.booking.hotel.models;

import com.example.booking.booking.models.Booking;
import com.example.booking.room.models.Room;
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
  @Column(name = "stars")
  private int stars;

  @Column(name = "img")
  private String img;

  @NotNull
  @Column(name = "created_at")
  private Long createdAt;

  @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL)
  @JsonIgnore
  private List<Room> rooms = new ArrayList<>();

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
  private List<Booking> booking = new ArrayList<>();

}
