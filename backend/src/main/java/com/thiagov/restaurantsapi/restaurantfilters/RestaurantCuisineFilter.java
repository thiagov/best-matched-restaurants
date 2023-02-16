package com.thiagov.restaurantsapi.restaurantfilters;

import com.thiagov.restaurantsapi.dtos.SearchRestaurantsInputDto;
import com.thiagov.restaurantsapi.models.Restaurant;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.function.Predicate;

@Component
public class RestaurantCuisineFilter  implements RestaurantFilter {
  @Override
  public Predicate<Restaurant> buildPredicate(SearchRestaurantsInputDto inputDto) {
    String cuisine = Optional.ofNullable(inputDto.getCuisine()).orElse("");
    return (restaurant -> restaurant.getCuisine().getName().toLowerCase().matches(".*" + cuisine.toLowerCase() + ".*"));
  }
}
