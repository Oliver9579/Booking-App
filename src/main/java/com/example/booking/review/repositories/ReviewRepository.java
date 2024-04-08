package com.example.booking.review.repositories;

import com.example.booking.review.models.Review;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends CrudRepository<Review, Integer> {

  List<Review> findAll();

  Optional<Review> findById(Integer id);

}
