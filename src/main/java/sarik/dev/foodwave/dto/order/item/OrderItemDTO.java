package sarik.dev.foodwave.dto.order.item;

import lombok.Data;
import sarik.dev.foodwave.dto.order.OrderDTO;
import sarik.dev.foodwave.dto.dish.DishDTO;

import java.math.BigDecimal;

@Data
public class OrderItemDTO {

    private Long id;
    private OrderDTO order;
    private DishDTO dish;
    private Integer quantity;
    private BigDecimal totalPrice;
}
