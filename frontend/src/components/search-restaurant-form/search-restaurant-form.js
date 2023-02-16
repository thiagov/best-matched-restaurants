import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { RestaurantList } from '../restaurant-list';

export const SearchRestaurantForm = () => {
  const [restaurants, setRestaurants] = useState([]);
  const [errorMessage, setErrorMessage] = useState(undefined);
  const [name, setName] = useState('');
  const [price, setPrice] = useState(undefined);
  const [rating, setRating] = useState(undefined);
  const [distance, setDistance] = useState(undefined);
  const [cuisine, setCuisine] = useState('');

  useEffect(() => {
    getRestaurants({});
  }, []);

  const getRestaurants = (params) => {
    axios.get('http://localhost:8080/api/restaurants', { params: params })
      .then(res => {
        setRestaurants(res.data);
      })
      .catch(err => {
        setErrorMessage(err.response.data);
      });
  }

  const onSubmit = (e) => {
    e.preventDefault();
    setErrorMessage(undefined);
    const params = {name, price, rating, distance, cuisine};
    getRestaurants(params);
  }

  return (
    <>
      <div className="w-full max-w-lg">
        <form onSubmit={onSubmit} className="bg-white shadow-md rounded px-8 pt-6 pb-8 mb-4">

          <ErrorAlert errorMessage={errorMessage}/>

          <div className="grid grid-cols-2 gap-4 mb-4">
            <Input type="text" name="name" value={name} label="Name" onChange={(e) => setName(e.target.value)}/>
            <Input type="number" name="rating" value={rating} label="Min rating" onChange={(e) => setRating(e.target.value)} min="1" max="5"/>
            <Input type="number" name="distance" value={distance} label="Max distance" onChange={(e) => setDistance(e.target.value)} min="1" max="10"/>
            <Input type="number" name="price" value={price} label="Max price" onChange={(e) => setPrice(e.target.value)} min="10" max="50"/>
            <Input type="text" name="cuisine" value={cuisine} label="Cuisine" onChange={(e) => setCuisine(e.target.value)}/>
          </div>

          <div className="flex flex-col justify-center">
            <button type="submit" className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline">Search</button>
          </div>

        </form>
      </div>

      <div className="w-full max-w-lg">
        <RestaurantList restaurants={restaurants}/>
      </div>
    </>
  )
};

const Input = ({ type, name, value, label, onChange, min, max }) => (
  <div className="mb-4">
    <label className="block text-gray-700 text-sm font-bold mb-2">
      {label}
    </label>
    <input name={name} type={type} value={value || ''} onChange={onChange} min={min} max={max} className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline" />
  </div>
);

const ErrorAlert = ({ errorMessage }) => (
  errorMessage && 
    <div className="px-1 py-1 mb-1 border border-solid border-transparent rounded border-rose-600 bg-red-200 text-red-900 text-center text-sm">
      {errorMessage}
    </div>
);
