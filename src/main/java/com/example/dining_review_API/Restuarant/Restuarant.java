package com.example.dining_review_API.Restuarant;

import com.example.dining_review_API.ReviewItem;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Dictionary;

@AllArgsConstructor(access= AccessLevel.PUBLIC)
public class Restuarant {
    @Getter @Setter private long id;
    @Getter @Setter private Dictionary<ReviewItem, Integer> reviews;
    @Getter @Setter private int overallReview;
}
