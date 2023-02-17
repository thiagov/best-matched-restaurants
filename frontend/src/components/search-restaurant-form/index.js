import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { RestaurantList } from '../restaurant-list';
import Icon from '@mdi/react';
import { mdiFilterCog, mdiListBox } from '@mdi/js';

export const SearchRestaurantForm = () => {
  const [restaurants, setRestaurants] = useState([]);
  const [errorMessage, setErrorMessage] = useState();
  const [name, setName] = useState('');
  const [price, setPrice] = useState();
  const [rating, setRating] = useState();
  const [distance, setDistance] = useState();
  const [cuisine, setCuisine] = useState('');

  useEffect(() => {
    getRestaurants({});
  }, []);

  const getRestaurants = (params) => {
    axios.get('http://localhost:8080/api/restaurants', {params: params})
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
      <div className='pb-4 flex gap-2 text-gray-500 items-center text-sm w-full max-w-lg pl-0.5 justify-center sm:justify-start'>
        <Icon path={mdiFilterCog} size={0.7} />
        <span className='font-bold'>Filters</span>
      </div>
      <div className='w-full max-w-lg'>
        <form onSubmit={onSubmit} className='bg-white shadow-md rounded px-4 py-4 mb-4'>

          <ErrorAlert errorMessage={errorMessage} />

          <div className='grid grid-cols-1 sm:grid-cols-2 gap-4 mb-4'>
            <Input type='text' name='name' value={name} label='Name' onChange={(e) => setName(e.target.value)} />
            <Input type='number' name='rating' value={rating} label='Min rating' onChange={(e) => setRating(e.target.value)} min='1' max='5' />
            <Input type='number' name='distance' value={distance} label='Max distance' onChange={(e) => setDistance(e.target.value)} min='1' max='10' />
            <Input type='number' name='price' value={price} label='Max price' onChange={(e) => setPrice(e.target.value)} min='10' max='50' />
            <Input type='text' name='cuisine' value={cuisine} label='Cuisine' onChange={(e) => setCuisine(e.target.value)} />
          </div>

          <div className='flex flex-col justify-center'>
            <button type='submit' className='bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline'>Search</button>
          </div>

        </form>
      </div>

      <div className='w-full max-w-lg'>
        <div className='pb-4 flex gap-2 text-gray-500 items-center text-sm w-full max-w-lg pl-0.5 justify-center sm:justify-start'>
          <Icon path={mdiListBox} size={0.7} />
          <span className='font-bold'>Best matching restaurants</span>
        </div>
        {restaurants?.length > 0 ? (
          <RestaurantList restaurants={restaurants} />
        ) : (
          <div className='text-center'>No results found. Try changing some filters.</div>
        )}
      </div>
    </>
  )
};

const Input = ({type, name, value, label, onChange, min, max}) => (
  <div>
    <label className='block text-gray-700 text-sm font-bold mb-2'>
      {label}
    </label>
    <input
      name={name}
      type={type}
      value={value || ''}
      onChange={onChange}
      min={min}
      max={max}
      className='shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline'
    />
  </div>
);

const ErrorAlert = ({errorMessage}) => (
  errorMessage && 
    <div className='px-1 py-1 mb-1 border border-solid border-transparent rounded border-rose-600 bg-red-200 text-red-900 text-center text-sm'>
      {errorMessage}
    </div>
);
