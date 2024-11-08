package sarik.dev.foodwave.service;

import sarik.dev.foodwave.dto.restaurant.owner.RestaurantOwnerCreateDTO;
import sarik.dev.foodwave.dto.restaurant.owner.RestaurantOwnerDTO;
import sarik.dev.foodwave.dto.restaurant.owner.RestaurantOwnerUpdateDTO;

import java.util.List;
import java.util.UUID;

public interface RestaurantOwnerService {

    RestaurantOwnerDTO createRestaurantOwner(RestaurantOwnerCreateDTO ownerCreateDTO);

    RestaurantOwnerDTO getRestaurantOwnerById(UUID ownerId);

    List<RestaurantOwnerDTO> getVerifiedRestaurantOwners();

    RestaurantOwnerDTO updateRestaurantOwner(UUID ownerId, RestaurantOwnerUpdateDTO ownerUpdateDTO);

    void deleteRestaurantOwner(UUID ownerId);

    void verifyRestaurantOwner(UUID ownerId);
}
