import React from 'react';

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
  <div>{restaurant.customerRating} {restaurant.name}</div>
);
