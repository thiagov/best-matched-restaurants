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
class RestaurantRatingFilterTest {
  @InjectMocks
  RestaurantRatingFilter restaurantRatingFilter;

  @Test
  public void should_returnPredicateThatTestsTrue_when_restaurantRatingGreaterThanOrEqualsToInputRating() {
    SearchRestaurantsInputDto input = mock(SearchRestaurantsInputDto.class);
    when(input.getRating()).thenReturn(10);
    Restaurant restaurant = mock(Restaurant.class);
    when(restaurant.getCustomerRating()).thenReturn(15);

    boolean result = restaurantRatingFilter.buildPredicate(input).test(restaurant);

    assertTrue(result);
  }

  @Test
  public void should_returnPredicateThatTestsFalse_when_restaurantRatingLowerThanInputRating() {
    SearchRestaurantsInputDto input = mock(SearchRestaurantsInputDto.class);
    when(input.getRating()).thenReturn(11);
    Restaurant restaurant = mock(Restaurant.class);
    when(restaurant.getCustomerRating()).thenReturn(10);

    boolean result = restaurantRatingFilter.buildPredicate(input).test(restaurant);

    assertFalse(result);
  }
}