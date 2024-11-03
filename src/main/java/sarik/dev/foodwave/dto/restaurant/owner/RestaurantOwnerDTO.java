package sarik.dev.foodwave.dto.restaurant.owner;

import lombok.Data;
import sarik.dev.foodwave.dto.restaurant.RestaurantDTO;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Data
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
}
