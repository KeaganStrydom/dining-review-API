package com.example.dining_review_API.repository;

import com.example.dining_review_API.model.Restaurant;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface RestaurantRepository extends CrudRepository<Restaurant, Long> {
    Optional<Restaurant>  findByNameAndZipCode(String name, String zipCode);
    List<Restaurant> findByZipCodeAndEggScoreNotNullOrderByEggScore(String zipCode);
    List<Restaurant> findByZipCodeAndPeanutScoreNotNullOrderByPeanutScore(String zipCode);
    List<Restaurant> findByZipCodeAndDairyScoreNotNullOrderByDairyScore(String zipCode);
}
