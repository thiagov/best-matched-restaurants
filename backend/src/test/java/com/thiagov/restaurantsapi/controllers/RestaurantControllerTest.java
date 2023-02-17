package com.thiagov.restaurantsapi.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class RestaurantControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void should_returnAtMostFiveRestaurants_when_searchMatchesMoreThanFive() throws Exception {
        this.mockMvc.perform(get("/api/restaurants"))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json"))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$.length()", lessThanOrEqualTo(5)));
    }

    @Test
    public void should_returnChowTable_when_distanceIsOneMaxPriceIsTenAndCuisineIsChinese() throws Exception {
        this.mockMvc
            .perform(get("/api/restaurants")
                .param("distance", "1")
                .param("price", "10")
                .param("cuisine", "chinese"))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json"))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$.[0].name", is("Chow Table")))
            .andExpect(jsonPath("$.length()", is(1)));
    }

    @Test
    public void should_returnBadRequest_when_outOfBoundsParams() throws Exception {
        this.mockMvc
            .perform(get("/api/restaurants")
                .param("price", "100"))
            .andExpect(status().isBadRequest());
    }

    @Test
    public void should_returnBadRequest_when_cantConvertParam() throws Exception {
        this.mockMvc
            .perform(get("/api/restaurants")
                .param("rating", "this is not right"))
            .andExpect(status().isBadRequest());
    }
}
