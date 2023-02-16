package com.thiagov.restaurantsapi.restaurantfilters;

import com.thiagov.restaurantsapi.dtos.SearchRestaurantsInputDto;
import com.thiagov.restaurantsapi.models.Restaurant;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.function.Predicate;

@Component
public class RestaurantDistanceFilter  implements RestaurantFilter {
  @Override
  public Predicate<Restaurant> buildPredicate(SearchRestaurantsInputDto inputDto) {
    int distance = Optional.ofNullable(inputDto.getDistance()).orElse(10);
    return (restaurant -> restaurant.getDistance() <= distance);
  }
}
