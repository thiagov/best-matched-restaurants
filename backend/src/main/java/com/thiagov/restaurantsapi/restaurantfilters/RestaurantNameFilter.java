package com.thiagov.restaurantsapi.restaurantfilters;

import com.thiagov.restaurantsapi.dtos.SearchRestaurantsInputDto;
import com.thiagov.restaurantsapi.models.Restaurant;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.function.Predicate;

@Component
public class RestaurantNameFilter implements RestaurantFilter {
  @Override
  public Predicate<Restaurant> buildPredicate(SearchRestaurantsInputDto inputDto) {
    String name = Optional.ofNullable(inputDto.getName()).orElse("");
    return (restaurant -> restaurant.getName().toLowerCase().matches(".*" + name.toLowerCase() + ".*"));
  }
}
