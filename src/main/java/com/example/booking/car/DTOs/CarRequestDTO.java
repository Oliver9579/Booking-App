package com.example.booking.car.DTOs;

import lombok.Data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public abstract class CarRequestDTO {

  private String pickUpLocation;
  private String pickUpDate;
  private String dropOffDate;

  public CarRequestDTO(String pickUpLocation, String pickUpDate, String dropOffDate) {
    this.pickUpLocation = pickUpLocation;
    this.pickUpDate = pickUpDate;
    this.dropOffDate = dropOffDate;
  }

  public Date getPickUpDate() {
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    Date date = new Date();
    try {
      date = formatter.parse(pickUpDate);
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return date;
  }

  public Date getDropOffDate() {
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    Date date = new Date();
    try {
      date = formatter.parse(dropOffDate);
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return date;
  }

  public String getPickUpDateString() {
    return pickUpDate;
  }

  public String getDropOffDateString() {
    return dropOffDate;
  }

}
