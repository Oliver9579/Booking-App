package com.example.booking.room.services;

import com.example.booking.hotel.models.Hotel;
import com.example.booking.room.DTOs.RoomDTO;
import com.example.booking.room.models.Room;
import com.example.booking.room.models.RoomType;
import com.example.booking.room.repositories.RoomRepository;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RoomServiceImpl implements RoomService {

  private RoomRepository roomRepository;

  @SneakyThrows
  @Override
  public Boolean isRoomAvailable(Room room, String checkInDate, String checkOutDate) {
    Boolean isAvailable = true;
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
    String[] unavailableDates = room.getUnavailable().split(",");
    Date checkIn = formatter.parse(checkInDate);
    Date checkOut = formatter.parse(checkOutDate);
    for (int i = 0; i < unavailableDates.length; i = i + 2) {
      Date unavailableStartDate = formatter.parse(unavailableDates[i]);
      Date unavailableEndDate = formatter.parse(unavailableDates[i + 1]);
      if (unavailableStartDate.before(checkIn) && unavailableEndDate.after(checkIn) ||
              unavailableStartDate.before(checkOut) && unavailableEndDate.after(checkOut) ||
              unavailableStartDate.after(checkIn) && unavailableEndDate.before(checkOut) ||
              unavailableStartDate.equals(checkIn) || unavailableEndDate.equals(checkIn) ||
              unavailableStartDate.equals(checkOut) || unavailableEndDate.equals(checkOut)) {
        isAvailable = false;
      }
    }
    return isAvailable;
  }

  @Override
  public List<RoomDTO> convertToRoomDTO(List<Room> rooms) {
    return rooms.stream()
            .map(room -> new RoomDTO(room.getId(), room.getRoomType(), room.getCapacity(), room.getPricePerNight()))
            .collect(Collectors.toList());
  }

}
