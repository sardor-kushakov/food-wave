package sarik.dev.foodwave.service;

import sarik.dev.foodwave.dto.cart.item.CartItemCreateDTO;
import sarik.dev.foodwave.dto.cart.item.CartItemDTO;
import sarik.dev.foodwave.dto.cart.item.CartItemUpdateDTO;

import java.util.List;
import java.util.UUID;

public interface CartItemService {

    CartItemDTO addCartItem(CartItemCreateDTO cartItemCreateDTO);

    CartItemDTO getCartItemById(UUID cartItemId);

    List<CartItemDTO> getCartItemsByCartId(UUID cartId);

    CartItemDTO updateCartItem(UUID cartItemId, CartItemUpdateDTO cartItemUpdateDTO);

    void deleteCartItem(UUID cartItemId);
}
