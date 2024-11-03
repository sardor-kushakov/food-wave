package sarik.dev.foodwave.dto.cart;

import lombok.Data;
import sarik.dev.foodwave.dto.cart.item.CartItemDTO;
import sarik.dev.foodwave.dto.user.UserDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Data
public class CartDTO {

    private UUID id;
    private UserDTO user;
    private Set<CartItemDTO> items;
    private boolean isCheckedOut;
    private BigDecimal totalPrice;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
