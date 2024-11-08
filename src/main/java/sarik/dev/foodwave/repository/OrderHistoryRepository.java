package sarik.dev.foodwave.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sarik.dev.foodwave.entity.admin.OrderHistory;
import sarik.dev.foodwave.entity.user.Order;
import sarik.dev.foodwave.entity.user.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrderHistoryRepository extends JpaRepository<OrderHistory, UUID> {

    List<OrderHistory> findByUser(User user);

    Optional<OrderHistory> findByOrder(Order order);
}
