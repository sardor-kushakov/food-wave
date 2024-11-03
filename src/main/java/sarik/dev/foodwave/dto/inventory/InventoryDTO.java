package sarik.dev.foodwave.dto.inventory;

import lombok.Data;
import sarik.dev.foodwave.dto.restaurant.RestaurantDTO;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class InventoryDTO {

    private UUID id;
    private String inventoryName;
    private double quantity;
    private String unit;
    private LocalDateTime addedAt;
    private LocalDateTime lastUpdated;
    private RestaurantDTO restaurant;
}
