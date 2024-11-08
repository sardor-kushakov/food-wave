package sarik.dev.foodwave.dto.menu;

import lombok.Builder;
import lombok.Data;
import sarik.dev.foodwave.dto.menu.item.MenuItemDTO;
import sarik.dev.foodwave.dto.restaurant.RestaurantDTO;
import sarik.dev.foodwave.entity.admin.Menu;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@Builder
public class MenuDTO {

    private UUID id;
    private String name;
    private String description;
    private RestaurantDTO restaurant;
    private Set<MenuItemDTO> items;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    /**
     * Converts a Menu entity to a MenuDTO.
     *
     * @param menu the Menu entity
     * @return a MenuDTO with data from the entity
     */
    public static MenuDTO fromEntity(Menu menu) {
        return MenuDTO.builder()
                .id(menu.getId())
                .name(menu.getName())
                .description(menu.getDescription())
                .restaurant(RestaurantDTO.fromEntity(menu.getRestaurant())) // Assuming RestaurantDTO has a fromEntity method
                .items(menu.getItems().stream()
                        .map(MenuItemDTO::fromEntity) // Assuming MenuItemDTO has a fromEntity method
                        .collect(Collectors.toSet()))
                .createdAt(menu.getCreatedAt())
                .updatedAt(menu.getUpdatedAt())
                .build();
    }
}
