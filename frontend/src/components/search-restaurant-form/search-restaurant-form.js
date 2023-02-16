import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { RestaurantList } from '../restaurant-list';

export const SearchRestaurantForm = () => {
  const [restaurants, setRestaurants] = useState([]);

  useEffect(() => {
    getRestaurants({});
  }, []);

  const getRestaurants = (params) => {
    axios.get('http://localhost:8080/api/restaurants', { params: params })
      .then(res => {
        console.log(res);
        setRestaurants(res.data);
      });
  }

  const onSubmit = (e) => {
    e.preventDefault();
    const form = e.target;
    const formData = new FormData(form);
    const formJson = Object.fromEntries(formData.entries());
    getRestaurants(formJson);
  }

  return (
    <>
      <form onSubmit={onSubmit}>
        <label>
          Name: <input name="name" />
        </label>
        <hr />
        <label>
          Rating: <input type="number" name="rating" />
        </label>
        <hr />
        <label>
          Distance: <input type="number" name="distance" />
        </label>
        <hr />
        <label>
          Price: <input type="number" name="price" />
        </label>
        <hr />
        <label>
          Cuisine: <input name="cuisine" />
        </label>
        <hr />
        <button type="submit">Search</button>
      </form>

      <RestaurantList restaurants={restaurants}/>
    </>
  )
};
