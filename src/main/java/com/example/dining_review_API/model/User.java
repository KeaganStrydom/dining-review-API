package com.example.dining_review_API.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Dictionary;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class User {
    private Long id;

    private String displayName;

    private String city;
    private String state;
    private String zipcode;

    private Boolean reviewsPeanut;
    private Boolean reviewsDairy;
    private Boolean reviewsEggs;
}
