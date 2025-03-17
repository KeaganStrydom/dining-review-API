package com.example.dining_review_API.repository;

import com.example.dining_review_API.model.Restuarant;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RestuarantRepository extends CrudRepository<Restuarant, Long> {
    List<Restuarant> findByNameAndZipCode(String name, String zipCode);
    List<Restuarant> findByZipCodeAndEggScoreNotNullOrderByEggScore(String zipCode);
    List<Restuarant> findByZipCodeAndPeanutScoreNotNullOrderByPeanutScore(String zipCode);
    List<Restuarant> findByZipCodeAndDairyScoreNotNullOrderByDairyScore(String zipCode);
}
