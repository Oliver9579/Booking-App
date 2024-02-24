package com.example.booking.room.services;

import com.example.booking.date.services.DaysService;
import com.example.booking.hotel.models.Hotel;
import com.example.booking.room.DTOs.RoomDTO;
import com.example.booking.room.models.Room;
import com.example.booking.room.models.RoomType;
import com.example.booking.room.repositories.RoomRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class RoomServiceImpl implements RoomService {

  private RoomRepository roomRepository;
  private DaysService daysService;

  @Override
  public Boolean isRoomAvailable(Room room, String checkInDate, String checkOutDate) {
    boolean isAvailable = true;
    List<String> travelDates = daysService.getFullTravelDates(checkInDate, checkOutDate);
    for (String travelDate : travelDates) {
      if (room.getUnavailable().stream().anyMatch(days -> days.getDate().equals(travelDate))) {
        isAvailable = false;
      }
    }
    return isAvailable;
  }

  @Override
  public List<RoomDTO> convertToRoomDTO(List<Room> rooms, List<Long> availableNumbers,
                                        String checkInDate, String checkOutDate) {
    List<RoomDTO> result = new ArrayList<>();
    for (int i = 0; i < rooms.size(); i++) {
      result.add(new RoomDTO(rooms.get(i).getId(), rooms.get(i).getRoomType(),
              rooms.get(i).getCapacity(),
              rooms.get(i).getPricePerNight() * (daysService.getFullTravelDates(checkInDate, checkOutDate).size() - 1),
              availableNumbers.get(i)));
    }
    return result;
  }

  @Override
  public List<Room> getRoomsByType(Hotel hotel, RoomType[] roomTypes) {
    List<Room> responsesRooms = new ArrayList<>();
    for (RoomType roomType : roomTypes) {
      if (hotel.getRooms().stream().anyMatch(room -> room.getRoomType() == roomType)) {
        responsesRooms.add(hotel.getRooms().stream().filter(room -> room.getRoomType() == roomType).findFirst().get());
      }
    }
    return responsesRooms;
  }

  @Override
  public List<Long> getRoomsCountByType(List<Room> rooms) {
    List<Long> counts = new ArrayList<>();
    for (RoomType roomType : RoomType.values()) {
      if (rooms.stream().anyMatch(room -> room.getRoomType() == roomType)) {
        counts.add(rooms.stream().filter(room -> room.getRoomType() == roomType).count());
      }
    }
    return counts;
  }

}
