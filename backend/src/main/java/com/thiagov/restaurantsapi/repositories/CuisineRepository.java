package com.thiagov.restaurantsapi.repositories;

import com.thiagov.restaurantsapi.models.Cuisine;
import org.springframework.stereotype.Component;

@Component
public class CuisineRepository extends CsvRepository<Cuisine> {

  public CuisineRepository() {
    readCsv("classpath:csv-data/cuisines.csv");
  }

  @Override
  protected Cuisine instantiateObject(String[] csvLine) {
    return Cuisine.builder()
        .id(Integer.parseInt(csvLine[0]))
        .name(csvLine[1])
        .build();
  }

  public Cuisine findById(int id) {
    return findAll().stream().filter(item -> item.getId() == id).findFirst().orElse(null);
  }
}
