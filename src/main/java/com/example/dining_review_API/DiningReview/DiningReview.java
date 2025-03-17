package com.example.dining_review_API.DiningReview;

import com.example.dining_review_API.ReviewItem;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Dictionary;
import java.util.Optional;

@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class DiningReview {
    @Getter @Setter private String reviewName;
    @Getter @Setter private Long restuarantId;
    @Getter @Setter private Dictionary<ReviewItem, Integer> reviews;
    @Getter @Setter private Optional<String> comment;
}
