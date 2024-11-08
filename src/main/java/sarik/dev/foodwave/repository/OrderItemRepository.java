package sarik.dev.foodwave.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sarik.dev.foodwave.entity.user.Dish;
import sarik.dev.foodwave.entity.user.Order;
import sarik.dev.foodwave.entity.user.OrderItem;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, UUID> {

    List<OrderItem> findByOrder(Order order);

    List<OrderItem> findByDish(Dish dish);
}
