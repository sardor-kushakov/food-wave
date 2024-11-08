package sarik.dev.foodwave.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sarik.dev.foodwave.dto.inventory.InventoryCreateDTO;
import sarik.dev.foodwave.dto.inventory.InventoryDTO;
import sarik.dev.foodwave.dto.inventory.InventoryUpdateDTO;
import sarik.dev.foodwave.entity.admin.Inventory;
import sarik.dev.foodwave.entity.admin.Restaurant;
import sarik.dev.foodwave.repository.InventoryRepository;
import sarik.dev.foodwave.repository.RestaurantRepository;
import sarik.dev.foodwave.service.InventoryService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;
    private final RestaurantRepository restaurantRepository;

    @Override
    @Transactional
    public InventoryDTO addInventoryItem(InventoryCreateDTO inventoryCreateDTO) {
        Restaurant restaurant = restaurantRepository.findById(inventoryCreateDTO.getRestaurantId())
                .orElseThrow(() -> new IllegalArgumentException("Restaurant not found"));

        Inventory inventory = Inventory.builder()
                .ingredientName(inventoryCreateDTO.getInventoryName())
                .quantity(inventoryCreateDTO.getQuantity())
                .unit(inventoryCreateDTO.getUnit())
                .restaurant(restaurant)
                .build();

        inventoryRepository.save(inventory);
        return InventoryDTO.fromEntity(inventory);
    }

    @Override
    public InventoryDTO getInventoryItemById(UUID inventoryId) {
        Inventory inventory = inventoryRepository.findById(inventoryId)
                .orElseThrow(() -> new IllegalArgumentException("Inventory item not found"));
        return InventoryDTO.fromEntity(inventory);
    }

    @Override
    public List<InventoryDTO> getInventoryByRestaurant(UUID restaurantId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new IllegalArgumentException("Restaurant not found"));

        return inventoryRepository.findByRestaurant(restaurant).stream()
                .map(InventoryDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public InventoryDTO updateInventoryItem(UUID inventoryId, InventoryUpdateDTO inventoryUpdateDTO) {
        Inventory inventory = inventoryRepository.findById(inventoryId)
                .orElseThrow(() -> new IllegalArgumentException("Inventory item not found"));

        inventory.setIngredientName(inventoryUpdateDTO.getInventoryName());
        inventory.setQuantity(inventoryUpdateDTO.getQuantity());
        inventory.setUnit(inventoryUpdateDTO.getUnit());

        inventoryRepository.save(inventory);
        return InventoryDTO.fromEntity(inventory);
    }

    @Override
    @Transactional
    public void deleteInventoryItem(UUID inventoryId) {
        if (inventoryRepository.existsById(inventoryId)) {
            inventoryRepository.deleteById(inventoryId);
        } else {
            throw new IllegalArgumentException("Inventory item not found");
        }
    }

    @Override
    @Transactional
    public void increaseQuantity(UUID inventoryId, double amount) {
        Inventory inventory = inventoryRepository.findById(inventoryId)
                .orElseThrow(() -> new IllegalArgumentException("Inventory item not found"));

        inventory.updateQuantity(amount);
        inventoryRepository.save(inventory);
    }

    @Override
    @Transactional
    public void decreaseQuantity(UUID inventoryId, double amount) {
        Inventory inventory = inventoryRepository.findById(inventoryId)
                .orElseThrow(() -> new IllegalArgumentException("Inventory item not found"));

        if (inventory.hasSufficientQuantity(amount)) {
            inventory.updateQuantity(-amount);
            inventoryRepository.save(inventory);
        } else {
            throw new IllegalArgumentException("Insufficient quantity in inventory");
        }
    }

    @Override
    public boolean hasSufficientQuantity(UUID inventoryId, double requiredAmount) {
        Inventory inventory = inventoryRepository.findById(inventoryId)
                .orElseThrow(() -> new IllegalArgumentException("Inventory item not found"));

        return inventory.hasSufficientQuantity(requiredAmount);
    }
}
