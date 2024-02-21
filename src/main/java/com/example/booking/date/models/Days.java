package com.example.booking.date.models;

import com.example.booking.car.models.Car;
import com.example.booking.room.models.Room;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "dates")
public class Days {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @NotNull
  @Column(name = "date")
  private Date date;

  @JsonIgnore
  @ManyToMany
  @JoinTable(name = "room_dates",
          joinColumns = @JoinColumn(name = "date_id"),
          inverseJoinColumns = @JoinColumn(name = "room_id"))
  private List<Room> rooms = new ArrayList<>();

  @JsonIgnore
  @ManyToMany
  @JoinTable(name = "car_dates",
          joinColumns = @JoinColumn(name = "date_id"),
          inverseJoinColumns = @JoinColumn(name = "car_id"))
  private List<Car> cars = new ArrayList<>();

  public String getDate() {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    return dateFormat.format(date);
  }
}
