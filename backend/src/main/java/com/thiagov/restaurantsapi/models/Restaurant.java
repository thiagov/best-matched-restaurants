package com.thiagov.restaurantsapi.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Restaurant {
  private String name;
  private int customerRating;
  private int distance;
  private int price;
  private Cuisine cuisine;
}
