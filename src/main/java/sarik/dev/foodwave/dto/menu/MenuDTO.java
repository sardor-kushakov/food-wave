package sarik.dev.foodwave.dto.menu;

import lombok.Data;
import sarik.dev.foodwave.dto.menu.item.MenuItemDTO;
import sarik.dev.foodwave.dto.restaurant.RestaurantDTO;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Data
public class MenuDTO {

    private UUID id;
    private String name;
    private String description;
    private RestaurantDTO restaurant;
    private Set<MenuItemDTO> items;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
