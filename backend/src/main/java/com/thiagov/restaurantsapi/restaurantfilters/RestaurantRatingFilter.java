package com.thiagov.restaurantsapi.restaurantfilters;

import com.thiagov.restaurantsapi.dtos.SearchRestaurantsInputDto;
import com.thiagov.restaurantsapi.models.Restaurant;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.function.Predicate;

@Component
public class RestaurantRatingFilter  implements RestaurantFilter {
  @Override
  public Predicate<Restaurant> buildPredicate(SearchRestaurantsInputDto inputDto) {
    int rating = Optional.ofNullable(inputDto.getRating()).orElse(0);
    return (restaurant -> restaurant.getCustomerRating() >= rating);
  }
}
