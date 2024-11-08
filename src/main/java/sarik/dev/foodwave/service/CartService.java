package sarik.dev.foodwave.service;

import sarik.dev.foodwave.dto.cart.CartCreateDTO;
import sarik.dev.foodwave.dto.cart.CartDTO;
import sarik.dev.foodwave.dto.cart.item.CartItemDTO;

import java.util.UUID;

public interface CartService {

    CartDTO createCart(CartCreateDTO cartCreateDTO);

    CartDTO getCartById(UUID cartId);

    CartDTO getUserActiveCart(UUID userId);

    CartDTO addItemToCart(UUID cartId, CartItemDTO cartItemDTO);

    CartDTO removeItemFromCart(UUID cartId, UUID itemId);

    void checkoutCart(UUID cartId);
}
