package com.example.booking.booking.repositories;

import com.example.booking.booking.models.Booking;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface BookingRepository extends CrudRepository<Booking, Integer> {

  Optional<Booking> findById(Integer id);

  List<Booking> findAllByUserId(Integer userId);

}
