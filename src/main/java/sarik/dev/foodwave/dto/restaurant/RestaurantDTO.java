package sarik.dev.foodwave.dto.restaurant;

import lombok.Data;
import sarik.dev.foodwave.dto.dish.DishDTO;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Data
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
}
