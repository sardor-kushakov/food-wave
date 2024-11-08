package sarik.dev.foodwave.service;

import sarik.dev.foodwave.dto.restaurant.RestaurantCreateDTO;
import sarik.dev.foodwave.dto.restaurant.RestaurantDTO;
import sarik.dev.foodwave.dto.restaurant.RestaurantUpdateDTO;

import java.util.List;
import java.util.UUID;

public interface RestaurantService {

    RestaurantDTO createRestaurant(RestaurantCreateDTO restaurantCreateDTO);

    RestaurantDTO getRestaurantById(UUID restaurantId);

    List<RestaurantDTO> getRestaurantsByCity(String city);

    List<RestaurantDTO> getRestaurantsByState(String state);

    RestaurantDTO updateRestaurant(UUID restaurantId, RestaurantUpdateDTO restaurantUpdateDTO);

    void deleteRestaurant(UUID restaurantId);

    void updateRestaurantRating(UUID restaurantId, double newRating);
}
