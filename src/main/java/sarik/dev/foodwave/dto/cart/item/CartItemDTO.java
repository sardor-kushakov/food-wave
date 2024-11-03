package sarik.dev.foodwave.dto.cart.item;

import lombok.Data;
import sarik.dev.foodwave.dto.cart.CartDTO;
import sarik.dev.foodwave.dto.dish.DishDTO;

import java.math.BigDecimal;

@Data
public class CartItemDTO {

    private Long id;
    private CartDTO cart;
    private DishDTO dish;
    private Integer quantity;
    private BigDecimal totalPrice;
}
