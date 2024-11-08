package sarik.dev.foodwave.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sarik.dev.foodwave.entity.user.Cart;
import sarik.dev.foodwave.entity.user.CartItem;
import sarik.dev.foodwave.entity.user.Dish;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, UUID> {

    List<CartItem> findByCart(Cart cart);

    Optional<CartItem> findByCartAndDish(Cart cart, Dish dish);
}
