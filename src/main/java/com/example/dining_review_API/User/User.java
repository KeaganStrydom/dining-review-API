package com.example.dining_review_API.User;

import com.example.dining_review_API.ReviewItem;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Dictionary;

@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class User {
    @Getter @Setter private Long id;
    @Getter @Setter private String name;
    @Getter @Setter private String city;
    @Getter @Setter private String state;
    @Getter @Setter private String zipcode;
    @Getter @Setter private Dictionary<ReviewItem, Boolean> interests;
}
