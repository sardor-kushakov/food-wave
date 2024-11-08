package sarik.dev.foodwave.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sarik.dev.foodwave.dto.cart.CartCreateDTO;
import sarik.dev.foodwave.dto.cart.CartDTO;
import sarik.dev.foodwave.dto.cart.item.CartItemDTO;
import sarik.dev.foodwave.entity.user.Cart;
import sarik.dev.foodwave.entity.user.CartItem;
import sarik.dev.foodwave.entity.user.Dish;
import sarik.dev.foodwave.entity.user.User;
import sarik.dev.foodwave.repository.CartRepository;
import sarik.dev.foodwave.repository.UserRepository;
import sarik.dev.foodwave.service.CartService;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public CartDTO createCart(CartCreateDTO cartCreateDTO) {
        User user = userRepository.findById(cartCreateDTO.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Cart cart = Cart.builder()
                .user(user)
                .isCheckedOut(false)
                .totalPrice(BigDecimal.ZERO)
                .build();

        cartRepository.save(cart);
        return CartDTO.fromEntity(cart);
    }

    @Override
    public CartDTO getCartById(UUID cartId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new IllegalArgumentException("Cart not found"));
        return CartDTO.fromEntity(cart);
    }

    @Override
    public CartDTO getUserActiveCart(UUID userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Cart cart = cartRepository.findByUserAndIsCheckedOut(user, false)
                .orElseThrow(() -> new IllegalArgumentException("No active cart found for the user"));
        return CartDTO.fromEntity(cart);
    }

    @Override
    @Transactional
    public CartDTO addItemToCart(UUID cartId, CartItemDTO cartItemDTO) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new IllegalArgumentException("Cart not found"));

        CartItem cartItem = CartItem.builder()
                .dish(Dish.fromDTO(cartItemDTO.getDish())) // `dish` ni `Dish` entity ga o'tkazish
                .quantity(cartItemDTO.getQuantity())
                .cart(cart)
                .build();

        cartItem.calculateTotalPrice(); // Total price ni hisoblash

        cart.addItem(cartItem);
        cartRepository.save(cart);
        return CartDTO.fromEntity(cart);
    }

    @Override
    @Transactional
    public CartDTO removeItemFromCart(UUID cartId, UUID itemId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new IllegalArgumentException("Cart not found"));

        CartItem itemToRemove = cart.getItems().stream()
                .filter(item -> item.getId().equals(itemId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Item not found in the cart"));

        cart.removeItem(itemToRemove);
        cartRepository.save(cart);

        return CartDTO.fromEntity(cart);
    }

    @Override
    @Transactional
    public void checkoutCart(UUID cartId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new IllegalArgumentException("Cart not found"));

        cart.checkout();
        cartRepository.save(cart);
    }
}
