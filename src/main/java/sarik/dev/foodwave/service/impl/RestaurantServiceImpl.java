package sarik.dev.foodwave.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sarik.dev.foodwave.dto.restaurant.RestaurantCreateDTO;
import sarik.dev.foodwave.dto.restaurant.RestaurantDTO;
import sarik.dev.foodwave.dto.restaurant.RestaurantUpdateDTO;
import sarik.dev.foodwave.entity.admin.Restaurant;
import sarik.dev.foodwave.repository.RestaurantRepository;
import sarik.dev.foodwave.service.RestaurantService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;

    @Override
    @Transactional
    public RestaurantDTO createRestaurant(RestaurantCreateDTO restaurantCreateDTO) {
        Restaurant restaurant = Restaurant.builder()
                .name(restaurantCreateDTO.getName())
                .address(restaurantCreateDTO.getAddress())
                .city(restaurantCreateDTO.getCity())
                .state(restaurantCreateDTO.getState())
                .country(restaurantCreateDTO.getCountry())
                .zipCode(restaurantCreateDTO.getZipCode())
                .contactNumber(restaurantCreateDTO.getContactNumber())
                .rating(restaurantCreateDTO.getRating())
                .description(restaurantCreateDTO.getDescription())
                .imageUrl(restaurantCreateDTO.getImageUrl())
                .build();

        restaurantRepository.save(restaurant);
        return RestaurantDTO.fromEntity(restaurant);
    }

    @Override
    @Transactional(readOnly = true)
    public RestaurantDTO getRestaurantById(UUID restaurantId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new IllegalArgumentException("Restaurant not found"));
        return RestaurantDTO.fromEntity(restaurant);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RestaurantDTO> getRestaurantsByCity(String city) {
        return restaurantRepository.findByCityAndIsDeleted(city, false).stream()
                .map(RestaurantDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<RestaurantDTO> getRestaurantsByState(String state) {
        return restaurantRepository.findByStateAndIsDeleted(state, false).stream()
                .map(RestaurantDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public RestaurantDTO updateRestaurant(UUID restaurantId, RestaurantUpdateDTO restaurantUpdateDTO) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new IllegalArgumentException("Restaurant not found"));

        restaurant.setName(restaurantUpdateDTO.getName());
        restaurant.setAddress(restaurantUpdateDTO.getAddress());
        restaurant.setCity(restaurantUpdateDTO.getCity());
        restaurant.setState(restaurantUpdateDTO.getState());
        restaurant.setCountry(restaurantUpdateDTO.getCountry());
        restaurant.setZipCode(restaurantUpdateDTO.getZipCode());
        restaurant.setContactNumber(restaurantUpdateDTO.getContactNumber());
        restaurant.setRating(restaurantUpdateDTO.getRating());
        restaurant.setDescription(restaurantUpdateDTO.getDescription());
        restaurant.setImageUrl(restaurantUpdateDTO.getImageUrl());

        restaurantRepository.save(restaurant);
        return RestaurantDTO.fromEntity(restaurant);
    }

    @Override
    @Transactional
    public void deleteRestaurant(UUID restaurantId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new IllegalArgumentException("Restaurant not found"));
        restaurantRepository.delete(restaurant);
    }

    @Override
    @Transactional
    public void updateRestaurantRating(UUID restaurantId, double newRating) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new IllegalArgumentException("Restaurant not found"));

        restaurant.setRating(newRating);
        restaurantRepository.save(restaurant);
    }
}
