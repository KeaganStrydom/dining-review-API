package com.example.dining_review_API.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Dictionary;

@Getter
@Setter
@AllArgsConstructor(access= AccessLevel.PUBLIC)
public class Restuarant {
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
