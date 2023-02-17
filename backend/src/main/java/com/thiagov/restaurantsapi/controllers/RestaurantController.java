package com.thiagov.restaurantsapi.controllers;

import com.thiagov.restaurantsapi.dtos.RestaurantDto;
import com.thiagov.restaurantsapi.dtos.SearchRestaurantsInputDto;
import com.thiagov.restaurantsapi.exceptions.InvalidFilterException;
import com.thiagov.restaurantsapi.models.Restaurant;
import com.thiagov.restaurantsapi.services.RestaurantService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/restaurants")
public class RestaurantController {

  @Autowired
  private RestaurantService restaurantService;

  @CrossOrigin
  @GetMapping
  public List<RestaurantDto> searchRestaurants(
      @Valid SearchRestaurantsInputDto inputDto,
      BindingResult bindingResult
  ) {
    if (bindingResult.hasErrors()) {
      String message = bindingResult.getFieldErrors().stream()
          .map(fe -> fe.getField() + " has an invalid value")
          .collect(Collectors.joining(" "));
      throw new InvalidFilterException(message);
    }
    List<Restaurant> restaurants = restaurantService.searchRestaurant(inputDto);
    List<RestaurantDto> restaurantsDTO = restaurants.stream()
        .map(RestaurantDto::new)
        .collect(Collectors.toList());
    return restaurantsDTO;
  }

  @ExceptionHandler
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  public String handleException(InvalidFilterException e) {
    return e.getMessage();
  }
}
