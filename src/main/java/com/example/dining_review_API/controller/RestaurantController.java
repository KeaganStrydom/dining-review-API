package com.example.dining_review_API.controller;

import com.example.dining_review_API.model.Restaurant;
import com.example.dining_review_API.repository.RestaurantRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.Optional;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {
    final RestaurantRepository restaurantRepository;

    public RestaurantController(final RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Restaurant createRestaurant(@PathVariable Long id, @RequestBody Restaurant restaurant) {
        validateNewRestaurant(restaurant);
        return restaurantRepository.save(restaurant);
    }

    @GetMapping
    public Iterable<Restaurant> getAllRestaurants() {
        return this.restaurantRepository.findAll();
    }

    @GetMapping("/search")
    public Iterable<Restaurant> searchRestaurants(@RequestParam String zipCode,  @RequestParam String allergy) {
        validateZipCode(zipCode);

        Iterable<Restaurant> restaurants = Collections.EMPTY_LIST;
        if (allergy.equalsIgnoreCase("peanuts")) {
            restaurants = this.restaurantRepository.findByZipCodeAndPeanutScoreNotNullOrderByPeanutScore(zipCode);
        } else if (allergy.equalsIgnoreCase("dairy")) {
            restaurants = this.restaurantRepository.findByZipCodeAndDairyScoreNotNullOrderByDairyScore(zipCode);
        } else if (allergy.equalsIgnoreCase("eggs")) {
            restaurants = this.restaurantRepository.findByZipCodeAndEggScoreNotNullOrderByEggScore(zipCode);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        return restaurants;
    }


    private void validateNewRestaurant(Restaurant newRestaurant) {
        if (newRestaurant == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        validateZipCode(newRestaurant.getZipCode());
        // See if restaurant exists
        Optional<Restaurant> existingRestaurant = this.restaurantRepository.findByNameAndZipCode(newRestaurant.getName(), newRestaurant.getZipCode());
        if (existingRestaurant.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
    }

    private void validateZipCode(String zipCode) {
        final Pattern zipCodePattern = Pattern.compile("\\d{5}");
        if (zipCode == null || zipCode.isEmpty() || !zipCodePattern.matcher(zipCode).matches()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}
