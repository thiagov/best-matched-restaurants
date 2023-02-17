package com.thiagov.restaurantsapi.services;

import com.thiagov.restaurantsapi.restaurantfilters.RestaurantFilter;
import com.thiagov.restaurantsapi.dtos.SearchRestaurantsInputDto;
import com.thiagov.restaurantsapi.models.Restaurant;
import com.thiagov.restaurantsapi.repositories.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class RestaurantService {
  @Autowired
  private RestaurantRepository restaurantRepository;
  @Autowired
  private List<RestaurantFilter> filters;

  public List<Restaurant> searchRestaurant(SearchRestaurantsInputDto inputDto) {
    Predicate<Restaurant> filterPredicate = filters.stream()
        .map(f -> f.buildPredicate(inputDto))
        .reduce(r -> true, Predicate::and);

    Comparator<Restaurant> sortRule = Comparator.comparing(Restaurant::getDistance)
        .thenComparing(Restaurant::getCustomerRating, Comparator.reverseOrder())
        .thenComparing(Restaurant::getPrice);

    List<Restaurant> filteredRestaurants = restaurantRepository.findAll().stream()
        .filter(filterPredicate)
        .sorted(sortRule)
        .collect(Collectors.toList());

    return filteredRestaurants.subList(0, Math.min(filteredRestaurants.size(), 5));
  }
}
