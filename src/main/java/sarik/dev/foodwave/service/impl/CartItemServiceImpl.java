package sarik.dev.foodwave.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sarik.dev.foodwave.dto.cart.item.CartItemCreateDTO;
import sarik.dev.foodwave.dto.cart.item.CartItemDTO;
import sarik.dev.foodwave.dto.cart.item.CartItemUpdateDTO;
import sarik.dev.foodwave.entity.user.Cart;
import sarik.dev.foodwave.entity.user.CartItem;
import sarik.dev.foodwave.entity.user.Dish;
import sarik.dev.foodwave.repository.CartItemRepository;
import sarik.dev.foodwave.repository.CartRepository;
import sarik.dev.foodwave.repository.DishRepository;
import sarik.dev.foodwave.service.CartItemService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartItemServiceImpl implements CartItemService {

    private final CartItemRepository cartItemRepository;
    private final CartRepository cartRepository;
    private final DishRepository dishRepository;

    @Override
    @Transactional
    public CartItemDTO addCartItem(CartItemCreateDTO cartItemCreateDTO) {
        Cart cart = cartRepository.findById(cartItemCreateDTO.getCartId())
                .orElseThrow(() -> new IllegalArgumentException("Cart not found"));

        Dish dish = dishRepository.findById(cartItemCreateDTO.getDishId())
                .orElseThrow(() -> new IllegalArgumentException("Dish not found"));

        CartItem cartItem = CartItem.builder()
                .cart(cart)
                .dish(dish)
                .quantity(cartItemCreateDTO.getQuantity())
                .build();

        cartItem.calculateTotalPrice();
        cartItemRepository.save(cartItem);

        // Recalculate total price for the cart
        cart.recalculateTotalPrice();
        cartRepository.save(cart);

        return CartItemDTO.fromEntity(cartItem);
    }

    @Override
    public CartItemDTO getCartItemById(UUID cartItemId) {
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new IllegalArgumentException("Cart item not found"));
        return CartItemDTO.fromEntity(cartItem);
    }

    @Override
    public List<CartItemDTO> getCartItemsByCartId(UUID cartId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new IllegalArgumentException("Cart not found"));

        return cartItemRepository.findByCart(cart).stream()
                .map(CartItemDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public CartItemDTO updateCartItem(UUID cartItemId, CartItemUpdateDTO cartItemUpdateDTO) {
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new IllegalArgumentException("Cart item not found"));

        cartItem.setQuantity(cartItemUpdateDTO.getQuantity());
        cartItem.calculateTotalPrice();
        cartItemRepository.save(cartItem);

        // Recalculate total price for the cart
        cartItem.getCart().recalculateTotalPrice();
        cartRepository.save(cartItem.getCart());

        return CartItemDTO.fromEntity(cartItem);
    }

    @Override
    @Transactional
    public void deleteCartItem(UUID cartItemId) {
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new IllegalArgumentException("Cart item not found"));

        Cart cart = cartItem.getCart();
        cart.removeItem(cartItem);
        cartItemRepository.delete(cartItem);

        // Recalculate total price for the cart
        cart.recalculateTotalPrice();
        cartRepository.save(cart);
    }
}
