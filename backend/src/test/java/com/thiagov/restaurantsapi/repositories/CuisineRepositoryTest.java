package com.thiagov.restaurantsapi.repositories;

import com.thiagov.restaurantsapi.models.Cuisine;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class CuisineRepositoryTest {

  @Autowired
  CuisineRepository cuisineRepository;

  @Test
  public void should_returnAllCuisinesFromCsv() {
    assertTrue(cuisineRepository.findAll().size() == 19);
  }

  @Test
  public void should_returnCuisineWithSpecificId_when_correctIdGiven() {
    Cuisine result = cuisineRepository.findById(5);
    assertTrue(result.getId() == 5);
  }

  @Test
  public void should_returnNull_when_outOfBounds() {
    Cuisine result = cuisineRepository.findById(20);
    assertTrue(result == null);

    result = cuisineRepository.findById(0);
    assertTrue(result == null);
  }
}
