package com.example.booking.date.repositories;

import com.example.booking.date.models.Days;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface DaysRepository extends CrudRepository<Days, Integer> {

  @Query(value = "SELECT * FROM dates WHERE date = date_format(?1, '%Y-%m-%d')", nativeQuery = true)
  Days findByDate(String date);

}