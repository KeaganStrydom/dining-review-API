package com.example.dining_review_API.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@RequiredArgsConstructor(access= AccessLevel.PUBLIC)
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String line1;
    private String city;
    private String state;
    private String zipCode;

    private String peanutScore;
    private String dairyScore;
    private String eggScore;
    private int overallReview;
}
