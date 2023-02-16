package com.thiagov.restaurantsapi.dtos;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SearchRestaurantsInputDto {
  private String name;
  @Min(1) @Max(5)
  private Integer rating;
  @Min(1) @Max(10)
  private Integer distance;
  @Min(10) @Max(50)
  private Integer price;
  private String cuisine;
}
