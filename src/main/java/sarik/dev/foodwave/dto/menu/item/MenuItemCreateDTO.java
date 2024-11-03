package sarik.dev.foodwave.dto.menu.item;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class MenuItemCreateDTO {

    private UUID menuId;
    private UUID dishId;
    private BigDecimal price;
}
