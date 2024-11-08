package sarik.dev.foodwave.service;

import sarik.dev.foodwave.dto.inventory.InventoryCreateDTO;
import sarik.dev.foodwave.dto.inventory.InventoryDTO;
import sarik.dev.foodwave.dto.inventory.InventoryUpdateDTO;

import java.util.List;
import java.util.UUID;

public interface InventoryService {

    InventoryDTO addInventoryItem(InventoryCreateDTO inventoryCreateDTO);

    InventoryDTO getInventoryItemById(UUID inventoryId);

    List<InventoryDTO> getInventoryByRestaurant(UUID restaurantId);

    InventoryDTO updateInventoryItem(UUID inventoryId, InventoryUpdateDTO inventoryUpdateDTO);

    void deleteInventoryItem(UUID inventoryId);

    void increaseQuantity(UUID inventoryId, double amount);

    void decreaseQuantity(UUID inventoryId, double amount);

    boolean hasSufficientQuantity(UUID inventoryId, double requiredAmount);
}
