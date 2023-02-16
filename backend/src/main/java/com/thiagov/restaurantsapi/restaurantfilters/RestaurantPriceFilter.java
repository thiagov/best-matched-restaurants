package com.thiagov.restaurantsapi.restaurantfilters;

import com.thiagov.restaurantsapi.dtos.SearchRestaurantsInputDto;
import com.thiagov.restaurantsapi.models.Restaurant;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.function.Predicate;

@Component
public class RestaurantPriceFilter  implements RestaurantFilter {
  @Override
  public Predicate<Restaurant> buildPredicate(SearchRestaurantsInputDto inputDto) {
    int price = Optional.ofNullable(inputDto.getPrice()).orElse(50);
    return (restaurant -> restaurant.getPrice() <= price);
  }
}
