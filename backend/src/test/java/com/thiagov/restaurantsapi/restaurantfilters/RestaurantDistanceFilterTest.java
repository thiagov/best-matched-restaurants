package com.thiagov.restaurantsapi.restaurantfilters;

import com.thiagov.restaurantsapi.dtos.SearchRestaurantsInputDto;
import com.thiagov.restaurantsapi.models.Restaurant;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RestaurantDistanceFilterTest {
  @InjectMocks
  RestaurantDistanceFilter restaurantDistanceFilter;

  @Test
  public void should_returnPredicateThatTestsTrue_when_restaurantDistanceLowerThanOrEqualsToInputDistance() {
    SearchRestaurantsInputDto input = mock(SearchRestaurantsInputDto.class);
    when(input.getDistance()).thenReturn(10);
    Restaurant restaurant = mock(Restaurant.class);
    when(restaurant.getDistance()).thenReturn(9);

    boolean result = restaurantDistanceFilter.buildPredicate(input).test(restaurant);

    assertTrue(result);
  }

  @Test
  public void should_returnPredicateThatTestsFalse_when_restaurantDistanceGreaterThanInputDistance() {
    SearchRestaurantsInputDto input = mock(SearchRestaurantsInputDto.class);
    when(input.getDistance()).thenReturn(10);
    Restaurant restaurant = mock(Restaurant.class);
    when(restaurant.getDistance()).thenReturn(15);

    boolean result = restaurantDistanceFilter.buildPredicate(input).test(restaurant);

    assertFalse(result);
  }
}