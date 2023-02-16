package com.thiagov.restaurantsapi.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class RestaurantRepositoryTest {
    @Autowired
    RestaurantRepository restaurantRepository;

    @Test
    public void should_returnAllRestaurantsFromCsv() {
        assertTrue(restaurantRepository.findAll().size() == 200);
    }
}
