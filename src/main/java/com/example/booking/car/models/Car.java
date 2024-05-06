package com.example.booking.car.models;

import com.example.booking.booking.models.Booking;
import com.example.booking.date.models.Days;
import com.example.booking.review.models.Review;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cars")
public class Car {

  @Id
  @GeneratedValue
  private int id;

  @NotNull
  @Column(name = "brand")
  private String brand;

  @NotNull
  @Column(name = "model")
  private String model;

  @NotNull
  @Column(name = "car_type")
  @Enumerated(EnumType.STRING)
  private CarType carType;

  @NotNull
  @Column(name = "capacity")
  private int capacity;

  @NotNull
  @Column(name = "transmission_type")
  @Enumerated(EnumType.STRING)
  private TransmissionType transmissionType;

  @NotNull
  @Column(name = "pick_up_location")
  private String pickUpLocation;

  @NotNull
  @Column(name = "drop_off_location")
  private String dropOffLocation;

  @NotNull
  @Column(name = "price_per_day")
  private int pricePerDay;

  @Column(name = "img")
  private String img;

  @NotNull
  @Column(name = "created_at")
  private Long createdAt;

  @JsonIgnore
  @ManyToMany
  @JoinTable(name = "car_dates",
          joinColumns = @JoinColumn(name = "car_id"),
          inverseJoinColumns = @JoinColumn(name = "date_id"))
  private List<Days> unavailable = new ArrayList<>();

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
  private List<Booking> booking = new ArrayList<>();

  @OneToMany(mappedBy = "car", cascade = CascadeType.ALL)
  private List<Review> reviews = new ArrayList<>();

  public Car(String brand, String model, CarType carType, String pickUpLocation,
             String dropOffLocation, int pricePerDay) {
    this.brand = brand;
    this.model = model;
    this.carType = carType;
    this.pickUpLocation = pickUpLocation;
    this.dropOffLocation = dropOffLocation;
    this.pricePerDay = pricePerDay;
    this.createdAt = System.currentTimeMillis() / 1000;
  }

  public Car(int id, String brand, String model, CarType carType, int capacity, TransmissionType transmissionType,
             String pickUpLocation, String dropOffLocation, int pricePerDay, String img, List<Review> reviews,
             List<Days> unavailable) {
    this.id = id;
    this.brand = brand;
    this.model = model;
    this.carType = carType;
    this.capacity = capacity;
    this.transmissionType = transmissionType;
    this.pickUpLocation = pickUpLocation;
    this.dropOffLocation = dropOffLocation;
    this.pricePerDay = pricePerDay;
    this.img = img;
    this.reviews = reviews;
    this.unavailable = unavailable;
  }

}
