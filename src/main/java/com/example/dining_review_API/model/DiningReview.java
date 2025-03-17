package com.example.dining_review_API.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Dictionary;
import java.util.Optional;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class DiningReview {
    private long id;

    private String reviewerName;
    private Long restaurantId;
    private Optional<String> comment;

    private Integer peanutScore;
    private Integer dairyScore;
    private Integer eggScore;

    private ReviewStatus status;
}
