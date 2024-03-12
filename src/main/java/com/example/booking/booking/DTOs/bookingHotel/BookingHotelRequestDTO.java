package com.example.booking.booking.DTOs.bookingHotel;

import com.example.booking.booking.DTOs.BookingRequestDTO;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class BookingHotelRequestDTO extends BookingRequestDTO {

  @NotBlank
  private String endDate;
  @NotNull
  private int hotelId;
  @NotNull
  private List<BookingRoomDTO> rooms;


  public BookingHotelRequestDTO(String startDate, int totalPrice, String endDate, int hotelId, List<BookingRoomDTO> rooms) {
    super(startDate, totalPrice);
    this.endDate = endDate;
    this.hotelId = hotelId;
    this.rooms = rooms;
  }

  @Override
  public Date getStartDate() {
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    Date date = new Date();
    try {
      date = formatter.parse(formatter.format(super.getStartDate()));
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return date;
  }

  public Date getEndDate() {
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    Date date = new Date();
    try {
      date = formatter.parse(endDate);
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return date;
  }

  public String getEndDateInString() {
    return endDate;
  }

}