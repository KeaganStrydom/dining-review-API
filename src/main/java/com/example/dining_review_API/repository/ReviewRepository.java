package com.example.dining_review_API.repository;

import com.example.dining_review_API.model.DiningReview;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ReviewRepository extends CrudRepository<DiningReview, Long> {
    List<DiningReview> findByStatus(String status);
    List<DiningReview> findByRestaurantIdAndStatus(Long restaurantId, String status);
}
