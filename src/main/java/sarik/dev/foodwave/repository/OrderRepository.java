package sarik.dev.foodwave.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sarik.dev.foodwave.entity.user.Order;
import sarik.dev.foodwave.entity.user.User;
import sarik.dev.foodwave.enums.user.OrderStatus;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {

    List<Order> findByUser(User user);

    List<Order> findByStatus(OrderStatus status);
}
