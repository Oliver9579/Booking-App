package com.example.booking.car.models;

import com.example.booking.date.models.Days;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
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

  @NotNull
  @Column(name = "created_at")
  private Long createdAt;

  @JsonIgnore
  @ManyToMany(mappedBy = "cars", cascade = CascadeType.ALL)
  private List<Days> unavailable;

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
}
