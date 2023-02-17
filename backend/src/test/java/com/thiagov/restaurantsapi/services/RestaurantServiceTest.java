package com.thiagov.restaurantsapi.services;

import com.thiagov.restaurantsapi.dtos.SearchRestaurantsInputDto;
import com.thiagov.restaurantsapi.models.Cuisine;
import com.thiagov.restaurantsapi.models.Restaurant;
import com.thiagov.restaurantsapi.repositories.RestaurantRepository;
import com.thiagov.restaurantsapi.restaurantfilters.RestaurantFilter;
import com.thiagov.restaurantsapi.restaurantfilters.RestaurantNameFilter;
import com.thiagov.restaurantsapi.restaurantfilters.RestaurantPriceFilter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RestaurantServiceTest {
  @InjectMocks
  RestaurantService restaurantService;
  @Mock
  RestaurantRepository restaurantRepository;
  @Mock
  List<RestaurantFilter> filters;
  @Mock
  RestaurantNameFilter nameFilter;
  @Mock
  RestaurantPriceFilter priceFilter;
  List<Restaurant> exampleRestaurants;

  @BeforeAll
  public void setup() {
    Cuisine american = new Cuisine(1, "American");
    Cuisine chinese = new Cuisine(2, "Chinese");
    Cuisine french = new Cuisine(5, "French");
    Cuisine korean = new Cuisine(8, "Korean");
    Cuisine vietnamese = new Cuisine(9, "Vietnamese");
    Cuisine indian = new Cuisine(11, "Indian");
    Cuisine afrikan = new Cuisine(15, "Afrikan");
    exampleRestaurants = new ArrayList<>(Arrays.asList(
        new Restaurant("Dine Bar", 5, 10, 35, afrikan),
        new Restaurant("Grill Bar", 1, 9, 40, french),
        new Restaurant("Palaceocity", 1, 7, 25, afrikan),
        new Restaurant("Spicy", 2, 6, 10, vietnamese),
        new Restaurant("Acclaimed Yummy", 5, 8, 50, korean),
        new Restaurant("Deliciousgenix", 4, 1, 10, indian),
        new Restaurant("Cuts Delicious", 3, 9, 25, korean),
        new Restaurant("Bazaar Chow", 4, 1, 40, american),
        new Restaurant("Chow Table", 1, 1, 10, chinese)
    ));
  }

  @Test
  public void should_notGetAnyRestaurant_when_noRestaurantSatisfiesSomePredicate() {
    SearchRestaurantsInputDto input = mock(SearchRestaurantsInputDto.class);
    when(restaurantRepository.findAll()).thenReturn(exampleRestaurants);
    when(filters.stream()).thenReturn(Stream.of(nameFilter, priceFilter));
    when(nameFilter.buildPredicate(any(SearchRestaurantsInputDto.class))).thenReturn(x -> true);
    when(priceFilter.buildPredicate(any(SearchRestaurantsInputDto.class))).thenReturn(x -> false);

    List<Restaurant> result = restaurantService.searchRestaurant(input);

    assertTrue(result.isEmpty());
    verify(restaurantRepository).findAll();
  }

  @Test
  public void should_getAtMostFiveRestaurants_when_moreThanFiveRestaurantsSatisfiesSomePredicate() {
    SearchRestaurantsInputDto input = mock(SearchRestaurantsInputDto.class);
    when(restaurantRepository.findAll()).thenReturn(exampleRestaurants);
    when(filters.stream()).thenReturn(Stream.of(nameFilter, priceFilter));
    when(nameFilter.buildPredicate(any(SearchRestaurantsInputDto.class))).thenReturn(x -> true);
    when(priceFilter.buildPredicate(any(SearchRestaurantsInputDto.class))).thenReturn(x -> true);

    List<Restaurant> result = restaurantService.searchRestaurant(input);

    assertEquals(5, result.size());
    verify(restaurantRepository).findAll();
  }

  @Test
  public void should_getLessThanFiveRestaurants_when_lessThanFiveRestaurantsSatisfiesSomePredicate() {
    SearchRestaurantsInputDto input = mock(SearchRestaurantsInputDto.class);
    when(restaurantRepository.findAll()).thenReturn(exampleRestaurants);
    when(filters.stream()).thenReturn(Stream.of(nameFilter, priceFilter));
    when(nameFilter.buildPredicate(any(SearchRestaurantsInputDto.class))).thenReturn(x -> true);
    when(priceFilter.buildPredicate(any(SearchRestaurantsInputDto.class))).thenReturn(x -> x.getCustomerRating() <= 1);

    List<Restaurant> result = restaurantService.searchRestaurant(input);

    assertEquals(3, result.size());
    verify(restaurantRepository).findAll();
  }

  @Test
  public void should_getRestaurantsInCorrectOrder_when_restaurantsSatisfiesSomePredicate() {
    SearchRestaurantsInputDto input = mock(SearchRestaurantsInputDto.class);
    when(restaurantRepository.findAll()).thenReturn(exampleRestaurants);
    when(filters.stream()).thenReturn(Stream.of(nameFilter, priceFilter));
    when(nameFilter.buildPredicate(any(SearchRestaurantsInputDto.class))).thenReturn(x -> true);
    when(priceFilter.buildPredicate(any(SearchRestaurantsInputDto.class))).thenReturn(x -> true);
    List<Restaurant> expectedOrder = new ArrayList<>(Arrays.asList(
        exampleRestaurants.get(5),
        exampleRestaurants.get(7),
        exampleRestaurants.get(8),
        exampleRestaurants.get(3),
        exampleRestaurants.get(2)
    ));

    List<Restaurant> result = restaurantService.searchRestaurant(input);

    assertEquals(5, result.size());
    assertArrayEquals(expectedOrder.toArray(), result.toArray());
    verify(restaurantRepository).findAll();
  }
}
