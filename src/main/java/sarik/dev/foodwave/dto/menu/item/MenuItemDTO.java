package sarik.dev.foodwave.dto.menu.item;

import lombok.Data;
import sarik.dev.foodwave.dto.dish.DishDTO;
import sarik.dev.foodwave.dto.menu.MenuDTO;

import java.math.BigDecimal;

@Data
public class MenuItemDTO {

    private Long id;
    private MenuDTO menu;
    private DishDTO dish;
    private BigDecimal price;
}
