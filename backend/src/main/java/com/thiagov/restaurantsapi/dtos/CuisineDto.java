package com.thiagov.restaurantsapi.dtos;


import com.thiagov.restaurantsapi.models.Cuisine;
import lombok.Data;

/**
 * The Cuisine DTO used to send data to the front-end.
 *
 * @author thiagov
 */
@Data
public class CuisineDto {
  private String name;

  public CuisineDto(Cuisine cuisine) {
    if (cuisine != null) {
      this.name = cuisine.getName();
    }
  }
}
