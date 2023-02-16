package com.thiagov.restaurantsapi.restaurantfilters;

import com.thiagov.restaurantsapi.dtos.SearchRestaurantsInputDto;
import com.thiagov.restaurantsapi.models.Restaurant;

import java.util.function.Predicate;

public interface RestaurantFilter {
  Predicate<Restaurant> buildPredicate(SearchRestaurantsInputDto inputDto);
}
