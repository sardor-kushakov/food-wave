package sarik.dev.foodwave.dto.restaurant.owner;

import lombok.Builder;
import lombok.Data;
import sarik.dev.foodwave.dto.restaurant.RestaurantDTO;
import sarik.dev.foodwave.entity.admin.RestaurantOwner;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@Builder
public class RestaurantOwnerDTO {

    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private boolean isVerified;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Set<RestaurantDTO> restaurants;

    /**
     * Converts a RestaurantOwner entity to a RestaurantOwnerDTO.
     *
     * @param restaurantOwner the RestaurantOwner entity
     * @return a RestaurantOwnerDTO with data from the entity
     */
    public static RestaurantOwnerDTO fromEntity(RestaurantOwner restaurantOwner) {
        return RestaurantOwnerDTO.builder()
                .id(restaurantOwner.getId())
                .firstName(restaurantOwner.getFirstName())
                .lastName(restaurantOwner.getLastName())
                .email(restaurantOwner.getEmail())
                .phoneNumber(restaurantOwner.getPhoneNumber())
                .isVerified(restaurantOwner.isVerified())
                .createdAt(restaurantOwner.getCreatedAt())
                .updatedAt(restaurantOwner.getUpdatedAt())
                .restaurants(restaurantOwner.getRestaurants().stream()
                        .map(RestaurantDTO::fromEntity) // Assuming RestaurantDTO has a fromEntity method
                        .collect(Collectors.toSet()))
                .build();
    }
}
