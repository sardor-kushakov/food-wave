package sarik.dev.foodwave.dto.menu.item;

import lombok.Builder;
import lombok.Data;
import sarik.dev.foodwave.dto.dish.DishDTO;
import sarik.dev.foodwave.dto.menu.MenuDTO;
import sarik.dev.foodwave.entity.admin.MenuItem;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
public class MenuItemDTO {

    private UUID id;
    private MenuDTO menu;
    private DishDTO dish;
    private BigDecimal price;

    /**
     * Converts a MenuItem entity to a MenuItemDTO.
     *
     * @param menuItem the MenuItem entity
     * @return a MenuItemDTO with data from the entity
     */
    public static MenuItemDTO fromEntity(MenuItem menuItem) {
        return MenuItemDTO.builder()
                .id(menuItem.getId())
                .menu(MenuDTO.fromEntity(menuItem.getMenu())) // Assuming MenuDTO has a fromEntity method
                .dish(DishDTO.fromEntity(menuItem.getDish())) // Assuming DishDTO has a fromEntity method
                .price(menuItem.getPrice() != null ? menuItem.getPrice() : menuItem.getDish().getPrice())
                .build();
    }
}
