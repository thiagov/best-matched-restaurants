package com.thiagov.restaurantsapi.services;

import com.thiagov.restaurantsapi.models.Cuisine;
import com.thiagov.restaurantsapi.repositories.CuisineRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CuisineServiceTest {
    @InjectMocks
    CuisineService cuisineService;

    @Mock
    CuisineRepository cuisineRepository;

    @Test
    public void should_getAllCuisines() {
        List<Cuisine> exampleCuisines = new ArrayList<>(Arrays.asList(
            new Cuisine(1, "American"),
            new Cuisine(2, "Chinese"),
            new Cuisine(3, "Thai"),
            new Cuisine(4, "Italian"),
            new Cuisine(5, "French")
        ));
        when(cuisineRepository.findAll()).thenReturn(exampleCuisines);

        List<Cuisine> result = cuisineService.getAllCuisines();

        assertEquals(5, result.size());
        verify(cuisineRepository).findAll();
    }
}
