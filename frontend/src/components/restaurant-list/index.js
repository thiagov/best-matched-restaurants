import React from 'react';
import logo from '../../logo.svg';

export const RestaurantList = ({ restaurants }) => (
  <div>
    {
      restaurants
        .map(restaurant =>
          <Restaurant key={restaurant.name} restaurant={restaurant} />
        )
    }
  </div>
);

const Restaurant = ({ restaurant }) => (
  <div className="bg-white shadow-md rounded px-4 pt-4 pb-4 mb-4">
    <div className="flex gap-4">
      <div className="flex w-32"><img src={logo} alt="Logo" /></div>
      <div className="flex-auto w-64">
        <RestaurantInfo label="Name" value={restaurant.name}/>
        <RestaurantInfo label="Rating" value={<Rating value={restaurant.customerRating}/>}/>
        <RestaurantInfo label="Price" value={`$${restaurant.price}`}/>
        <RestaurantInfo label="Distance" value={`${restaurant.distance} mile${restaurant.distance > 1 ? 's' : ''}`}/>
        <RestaurantInfo label="Cuisine" value={restaurant.cuisine.name}/>
      </div>
    </div>
  </div>
);

const Rating = ({ value }) => (
  <>
    {
      [...Array(value)].map((_value, index) => <span key={index} className="text-yellow-500 ml-0.5">&#9733;</span>)
    }
  </>
);

const RestaurantInfo = ({ label, value }) => (
  <div className="grid grid-cols-4 gap-2 text-sm py-0.5">
    <span className="text-left sm:text-right font-bold">{label}:</span>
    <span className="col-span-3">{value}</span>
  </div>
);
