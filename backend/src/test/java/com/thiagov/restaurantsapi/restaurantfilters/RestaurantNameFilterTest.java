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
class RestaurantNameFilterTest {
  @InjectMocks
  RestaurantNameFilter restaurantNameFilter;

  @Test
  public void should_returnPredicateThatTestsTrue_when_restaurantNameMatchesInputName() {
    SearchRestaurantsInputDto input = mock(SearchRestaurantsInputDto.class);
    when(input.getName()).thenReturn("fre");
    Restaurant restaurant = mock(Restaurant.class);
    when(restaurant.getName()).thenReturn("French");

    boolean result = restaurantNameFilter.buildPredicate(input).test(restaurant);

    assertTrue(result);
  }

  @Test
  public void should_returnPredicateThatTestsFalse_when_restaurantNameDoesNotMatchesInputName() {
    SearchRestaurantsInputDto input = mock(SearchRestaurantsInputDto.class);
    when(input.getName()).thenReturn("notMatch");
    Restaurant restaurant = mock(Restaurant.class);
    when(restaurant.getName()).thenReturn("French");

    boolean result = restaurantNameFilter.buildPredicate(input).test(restaurant);

    assertFalse(result);
  }
}