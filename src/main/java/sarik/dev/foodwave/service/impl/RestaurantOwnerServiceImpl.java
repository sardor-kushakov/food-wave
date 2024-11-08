package sarik.dev.foodwave.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sarik.dev.foodwave.dto.restaurant.owner.RestaurantOwnerCreateDTO;
import sarik.dev.foodwave.dto.restaurant.owner.RestaurantOwnerDTO;
import sarik.dev.foodwave.dto.restaurant.owner.RestaurantOwnerUpdateDTO;
import sarik.dev.foodwave.entity.admin.RestaurantOwner;
import sarik.dev.foodwave.repository.RestaurantOwnerRepository;
import sarik.dev.foodwave.service.RestaurantOwnerService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RestaurantOwnerServiceImpl implements RestaurantOwnerService {

    private final RestaurantOwnerRepository restaurantOwnerRepository;

    @Override
    @Transactional
    public RestaurantOwnerDTO createRestaurantOwner(RestaurantOwnerCreateDTO ownerCreateDTO) {
        RestaurantOwner owner = RestaurantOwner.builder()
                .firstName(ownerCreateDTO.getFirstName())
                .lastName(ownerCreateDTO.getLastName())
                .email(ownerCreateDTO.getEmail())
                .password(ownerCreateDTO.getPassword()) // Parolni shifrlash kerak
                .phoneNumber(ownerCreateDTO.getPhoneNumber())
                .isVerified(false)
                .build();

        restaurantOwnerRepository.save(owner);
        return RestaurantOwnerDTO.fromEntity(owner);
    }

    @Override
    public RestaurantOwnerDTO getRestaurantOwnerById(UUID ownerId) {
        RestaurantOwner owner = restaurantOwnerRepository.findById(ownerId)
                .orElseThrow(() -> new IllegalArgumentException("Restaurant owner not found"));
        return RestaurantOwnerDTO.fromEntity(owner);
    }

    @Override
    public List<RestaurantOwnerDTO> getVerifiedRestaurantOwners() {
        return restaurantOwnerRepository.findByIsVerified(true).stream()
                .map(RestaurantOwnerDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public RestaurantOwnerDTO updateRestaurantOwner(UUID ownerId, RestaurantOwnerUpdateDTO ownerUpdateDTO) {
        RestaurantOwner owner = restaurantOwnerRepository.findById(ownerId)
                .orElseThrow(() -> new IllegalArgumentException("Restaurant owner not found"));

        owner.setFirstName(ownerUpdateDTO.getFirstName());
        owner.setLastName(ownerUpdateDTO.getLastName());
        owner.setEmail(ownerUpdateDTO.getEmail());
        owner.setPassword(ownerUpdateDTO.getPassword()); // Parolni shifrlash kerak
        owner.setPhoneNumber(ownerUpdateDTO.getPhoneNumber());

        restaurantOwnerRepository.save(owner);
        return RestaurantOwnerDTO.fromEntity(owner);
    }

    @Override
    @Transactional
    public void deleteRestaurantOwner(UUID ownerId) {
        RestaurantOwner owner = restaurantOwnerRepository.findById(ownerId)
                .orElseThrow(() -> new IllegalArgumentException("Restaurant owner not found"));
        restaurantOwnerRepository.delete(owner);
    }

    @Override
    @Transactional
    public void verifyRestaurantOwner(UUID ownerId) {
        RestaurantOwner owner = restaurantOwnerRepository.findById(ownerId)
                .orElseThrow(() -> new IllegalArgumentException("Restaurant owner not found"));

        owner.verify();
        restaurantOwnerRepository.save(owner);
    }
}
