import React from 'react';
import Icon from '@mdi/react';
import {
  mdiSilverwareForkKnife,
  mdiNumeric1Circle,
  mdiNumeric2Circle,
  mdiNumeric3Circle,
} from '@mdi/js';

const RESTAURANT_CLASSIFICATION = [
  {
    icon: mdiNumeric1Circle,
    color: 'text-yellow-500',
  },
  {
    icon: mdiNumeric2Circle,
    color: 'text-stone-400',
  },
  {
    icon: mdiNumeric3Circle,
    color: 'text-amber-600',
  }
];

export const RestaurantList = ({restaurants}) => (
  <div>
    {
      restaurants
        .map((restaurant, idx) =>
          <Restaurant key={restaurant.name} restaurant={restaurant} classification={RESTAURANT_CLASSIFICATION[idx]}/>
        )
    }
  </div>
);

const Restaurant = ({restaurant, classification}) => (
  <div className='bg-white shadow-md rounded px-4 pt-4 pb-4 mb-4 relative'>
    {classification && <Icon path={classification.icon} size={1} className={`absolute top-3 left-3 ${classification.color}`}/>}
    <div className='flex gap-4'>
      <div className='flex w-32 items-center justify-center text-blue-500'>
        <Icon path={mdiSilverwareForkKnife} size={3} />
      </div>
      <div className='flex-auto w-64'>
        <RestaurantInfo label='Name' value={restaurant.name} />
        <RestaurantInfo label='Rating' value={<Rating value={restaurant.customerRating} />} />
        <RestaurantInfo label='Price' value={`$${restaurant.price} on average`} />
        <RestaurantInfo label='Distance' value={`${restaurant.distance} mile${restaurant.distance > 1 ? 's' : ''}`} />
        <RestaurantInfo label='Cuisine' value={restaurant.cuisine.name} />
      </div>
    </div>
  </div>
);

const Rating = ({value}) => (
  <>
    {
      [...Array(value)].map((_value, index) => <span key={index} className='text-yellow-500 ml-0.5'>&#9733;</span>)
    }
  </>
);

const RestaurantInfo = ({label, value}) => (
  <div className='grid grid-cols-4 gap-2 text-sm py-0.5'>
    <span className='text-left sm:text-right font-bold'>{label}:</span>
    <span className='col-span-3'>{value}</span>
  </div>
);
