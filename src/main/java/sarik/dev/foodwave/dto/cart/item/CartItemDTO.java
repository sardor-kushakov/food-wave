package sarik.dev.foodwave.dto.cart.item;

import lombok.Data;
import sarik.dev.foodwave.dto.dish.DishDTO;
import sarik.dev.foodwave.entity.user.CartItem;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class CartItemDTO {
    private UUID id;
    private DishDTO dish; // DishDTO ni qo'llang
    private Integer quantity;
    private BigDecimal totalPrice;

    public static CartItemDTO fromEntity(CartItem cartItem) {
        CartItemDTO dto = new CartItemDTO();
        dto.setId(cartItem.getId());
        dto.setDish(DishDTO.fromEntity(cartItem.getDish())); // Dish entity sini DTO ga o'tkazish
        dto.setQuantity(cartItem.getQuantity());
        dto.setTotalPrice(cartItem.getTotalPrice());
        return dto;
    }

    public UUID getDishId() {
        return dish != null ? dish.getId() : null; // Agar dish bo'lmasa null qaytaradi
    }

    public BigDecimal getUnitPrice() {
        return dish != null ? dish.getPrice() : BigDecimal.ZERO; // Agar dish bo'lmasa 0 qaytaradi
    }
}
