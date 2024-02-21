package com.example.booking.room.services;

import com.example.booking.room.DTOs.RoomDTO;
import com.example.booking.room.models.Room;
import com.example.booking.room.repositories.RoomRepository;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RoomServiceImpl implements RoomService {

  private RoomRepository roomRepository;

  @Override
  public Boolean isRoomAvailable(Room room, String checkInDate, String checkOutDate) {
    boolean isAvailable = true;
    List<String> travelDates = getFullTravelDates(checkInDate, checkOutDate);
    for (String travelDate : travelDates) {
      if (room.getUnavailable().stream().anyMatch(days -> days.getDate().equals(travelDate))) {
        isAvailable = false;
      }
    }
    return isAvailable;
  }

  private List<String> getFullTravelDates(String checkInDate, String checkOutDate) {
    DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    LocalDate firstDate = LocalDate.parse(checkInDate, dateFormat);
    LocalDate lastDate = LocalDate.parse(checkOutDate, dateFormat);
    long travelLength = ChronoUnit.DAYS.between(firstDate, lastDate);
    List<String> dates = new ArrayList<>();
    for (int i = 0; i <= travelLength; i++) {
      dates.add(firstDate.plusDays(i).format(dateFormat));
    }
    return dates;
  }

  @Override
  public List<RoomDTO> convertToRoomDTO(List<Room> rooms) {
    return rooms.stream()
            .map(room -> new RoomDTO(room.getId(), room.getRoomType(), room.getCapacity(), room.getPricePerNight()))
            .collect(Collectors.toList());
  }

}
