package sarik.dev.foodwave.dto.restaurant;

import lombok.Builder;
import lombok.Data;
import sarik.dev.foodwave.dto.dish.DishDTO;
import sarik.dev.foodwave.entity.admin.Restaurant;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@Builder
public class RestaurantDTO {

    private UUID id;
    private String name;
    private String address;
    private String city;
    private String state;
    private String country;
    private String zipCode;
    private String contactNumber;
    private Double rating;
    private String description;
    private String imageUrl;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Set<DishDTO> dishes;

    /**
     * Converts a Restaurant entity to a RestaurantDTO.
     *
     * @param restaurant the Restaurant entity
     * @return a RestaurantDTO with data from the entity
     */
    public static RestaurantDTO fromEntity(Restaurant restaurant) {
        return RestaurantDTO.builder()
                .id(restaurant.getId())
                .name(restaurant.getName())
                .address(restaurant.getAddress())
                .city(restaurant.getCity())
                .state(restaurant.getState())
                .country(restaurant.getCountry())
                .zipCode(restaurant.getZipCode())
                .contactNumber(restaurant.getContactNumber())
                .rating(restaurant.getRating())
                .description(restaurant.getDescription())
                .imageUrl(restaurant.getImageUrl())
                .createdAt(restaurant.getCreatedAt())
                .updatedAt(restaurant.getUpdatedAt())
                .dishes(restaurant.getDishes().stream()
                        .map(DishDTO::fromEntity) // Assuming DishDTO has a fromEntity method
                        .collect(Collectors.toSet()))
                .build();
    }
}
