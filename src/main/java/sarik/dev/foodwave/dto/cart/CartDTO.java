package sarik.dev.foodwave.dto.cart;

import lombok.Data;
import sarik.dev.foodwave.dto.cart.item.CartItemDTO;
import sarik.dev.foodwave.dto.user.UserDTO;
import sarik.dev.foodwave.entity.user.Cart;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
public class CartDTO {

    private UUID id;
    private UserDTO user;
    private Set<CartItemDTO> items;
    private boolean isCheckedOut;
    private BigDecimal totalPrice;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    /**
     * Converts a Cart entity to a CartDTO.
     *
     * @param cart the Cart entity
     * @return a CartDTO with data from the entity
     */
    public static CartDTO fromEntity(Cart cart) {
        return CartDTO.builder()
                .id(cart.getId())
                .user(UserDTO.fromEntity(cart.getUser())) // Assuming UserDTO has a fromEntity method
                .items(cart.getItems().stream()
                        .map(CartItemDTO::fromEntity) // Assuming CartItemDTO has a fromEntity method
                        .collect(Collectors.toSet()))
                .isCheckedOut(cart.isCheckedOut())
                .totalPrice(cart.getTotalPrice())
                .createdAt(cart.getCreatedAt())
                .updatedAt(cart.getUpdatedAt())
                .build();
    }
}
