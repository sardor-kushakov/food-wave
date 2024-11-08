package sarik.dev.foodwave.dto.inventory;

import lombok.Builder;
import lombok.Data;
import sarik.dev.foodwave.dto.restaurant.RestaurantDTO;
import sarik.dev.foodwave.entity.admin.Inventory;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class InventoryDTO {

    private UUID id;
    private String inventoryName;
    private double quantity;
    private String unit;
    private LocalDateTime addedAt;
    private LocalDateTime lastUpdated;
    private RestaurantDTO restaurant;

    /**
     * Converts an Inventory entity to an InventoryDTO.
     *
     * @param inventory the Inventory entity
     * @return an InventoryDTO with data from the entity
     */
    public static InventoryDTO fromEntity(Inventory inventory) {
        return InventoryDTO.builder()
                .id(inventory.getId())
                .inventoryName(inventory.getIngredientName()) // assuming `inventoryName` refers to `ingredientName`
                .quantity(inventory.getQuantity())
                .unit(inventory.getUnit())
                .addedAt(inventory.getAddedAt())
                .lastUpdated(inventory.getLastUpdated())
                .restaurant(RestaurantDTO.fromEntity(inventory.getRestaurant()))
                .build();
    }
}
