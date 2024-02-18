package com.example.booking.room.repositories;

import com.example.booking.room.models.Room;
import com.example.booking.room.models.RoomType;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RoomRepository extends CrudRepository<Room, Integer> {

  Optional<Room> findById(Integer id);

}
