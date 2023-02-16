package com.thiagov.restaurantsapi.restaurantfilters;

import com.thiagov.restaurantsapi.dtos.SearchRestaurantsInputDto;
import com.thiagov.restaurantsapi.models.Cuisine;
import com.thiagov.restaurantsapi.models.Restaurant;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RestaurantCuisineFilterTest {
  @InjectMocks
  RestaurantCuisineFilter restaurantCuisineFilter;

  @Test
  public void should_returnPredicateThatTestsTrue_when_restaurantCuisineMatchesInputCuisine() {
    SearchRestaurantsInputDto input = mock(SearchRestaurantsInputDto.class);
    when(input.getCuisine()).thenReturn("fre");
    Cuisine cuisine = mock(Cuisine.class);
    when(cuisine.getName()).thenReturn("French");
    Restaurant restaurant = mock(Restaurant.class);
    when(restaurant.getCuisine()).thenReturn(cuisine);

    boolean result = restaurantCuisineFilter.buildPredicate(input).test(restaurant);

    assertTrue(result);
  }

  @Test
  public void should_returnPredicateThatTestsFalse_when_restaurantCuisineDoesNotMatchesInputCuisine() {
    SearchRestaurantsInputDto input = mock(SearchRestaurantsInputDto.class);
    when(input.getCuisine()).thenReturn("oth");
    Cuisine cuisine = mock(Cuisine.class);
    when(cuisine.getName()).thenReturn("French");
    Restaurant restaurant = mock(Restaurant.class);
    when(restaurant.getCuisine()).thenReturn(cuisine);

    boolean result = restaurantCuisineFilter.buildPredicate(input).test(restaurant);

    assertFalse(result);
  }
}