package com.thiagov.restaurantsapi.repositories;

import com.thiagov.restaurantsapi.models.Restaurant;
import org.springframework.stereotype.Component;

@Component
public class RestaurantRepository extends CsvRepository<Restaurant> {
  private CuisineRepository cuisineRepository;

  public RestaurantRepository(CuisineRepository cuisineRepository) {
    this.cuisineRepository = cuisineRepository;
    readCsv("classpath:csv-data/restaurants.csv");
  }

  @Override
  protected Restaurant instantiateObject(String[] csvLine) {
    return Restaurant.builder()
        .name(csvLine[0])
        .customerRating(Integer.parseInt(csvLine[1]))
        .distance(Integer.parseInt(csvLine[2]))
        .price(Integer.parseInt(csvLine[3]))
        .cuisine(cuisineRepository.findById(Integer.parseInt(csvLine[4])))
        .build();
  }
}
