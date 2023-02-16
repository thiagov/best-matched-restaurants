package com.thiagov.restaurantsapi.services;

import com.thiagov.restaurantsapi.models.Cuisine;
import com.thiagov.restaurantsapi.repositories.CuisineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CuisineService {

  @Autowired
  private CuisineRepository cuisineRepository;

  public List<Cuisine> getAllCuisines() {
    return cuisineRepository.findAll();
  }
}
