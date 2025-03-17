package com.example.dining_review_API.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.util.Dictionary;

@Entity
@Getter
@Setter
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String displayName;

    private String city;
    private String state;
    private String zipcode;

    private Boolean reviewsPeanut;
    private Boolean reviewsDairy;
    private Boolean reviewsEggs;
}
