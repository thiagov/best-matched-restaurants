package com.thiagov.restaurantsapi.controllers;

import com.thiagov.restaurantsapi.dtos.CuisineDto;
import com.thiagov.restaurantsapi.models.Cuisine;
import com.thiagov.restaurantsapi.services.CuisineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/cuisines")
public class CuisineController {

  @Autowired
  private CuisineService cuisineService;

  @CrossOrigin
  @GetMapping
  public List<CuisineDto> getAllCuisines() {
    List<Cuisine> cuisines = cuisineService.getAllCuisines();
    return cuisines.stream().map(CuisineDto::new).collect(Collectors.toList());
  }
}
