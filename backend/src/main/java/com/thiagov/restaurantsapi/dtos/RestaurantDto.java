package com.thiagov.restaurantsapi.dtos;


import com.thiagov.restaurantsapi.models.Restaurant;
import lombok.Data;

/**
 * The Restaurant DTO used to send data to the front-end.
 *
 * @author thiagov
 */
@Data
public class RestaurantDto {
  private String name;
  private int customerRating;
  private int distance;
  private int price;
  private CuisineDto cuisine;

  public RestaurantDto(Restaurant restaurant) {
    if (restaurant != null) {
      this.name = restaurant.getName();
      this.customerRating = restaurant.getCustomerRating();
      this.distance = restaurant.getDistance();
      this.price = restaurant.getPrice();
      this.cuisine = new CuisineDto(restaurant.getCuisine());
    }
  }
}
