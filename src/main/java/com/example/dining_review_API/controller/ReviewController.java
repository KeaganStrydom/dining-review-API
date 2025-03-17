package com.example.dining_review_API.controller;

import com.example.dining_review_API.model.DiningReview;
import com.example.dining_review_API.model.Restaurant;
import com.example.dining_review_API.model.ReviewStatus;
import com.example.dining_review_API.model.User;
import com.example.dining_review_API.repository.RestaurantRepository;
import com.example.dining_review_API.repository.ReviewRepository;
import com.example.dining_review_API.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
    final private ReviewRepository reviewRepository;
    final private RestaurantRepository restaurantRepository;
    final private UserRepository userRepository;

    public ReviewController(ReviewRepository reviewRepository, RestaurantRepository restaurantRepository, UserRepository userRepository) {
        this.reviewRepository = reviewRepository;
        this.restaurantRepository = restaurantRepository;
        this.userRepository = userRepository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public DiningReview saveNewReview(@RequestBody DiningReview diningReview) {
        validateNewUserReview(diningReview);

        Optional<Restaurant> optionalRestaurant = this.restaurantRepository.findById(diningReview.getRestaurantId());
        if (optionalRestaurant.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
        }

        diningReview.setStatus(ReviewStatus.PENDING);
        return this.reviewRepository.save(diningReview);
    }


    private void validateNewUserReview(DiningReview newUserReview) {
        if (ObjectUtils.isEmpty(newUserReview.getReviewerName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        if (ObjectUtils.isEmpty(newUserReview.getRestaurantId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        if (ObjectUtils.isEmpty(newUserReview.getPeanutScore()) &&
                ObjectUtils.isEmpty(newUserReview.getDairyScore()) &&
                ObjectUtils.isEmpty(newUserReview.getEggScore())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        Optional<User> optionalUser = userRepository.findByDisplayName(newUserReview.getReviewerName());
        if (optionalUser.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }
    }


}
