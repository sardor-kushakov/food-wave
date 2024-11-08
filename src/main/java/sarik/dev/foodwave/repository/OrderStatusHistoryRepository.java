package sarik.dev.foodwave.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sarik.dev.foodwave.entity.user.OrderStatusHistory;
import sarik.dev.foodwave.entity.user.Order;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderStatusHistoryRepository extends JpaRepository<OrderStatusHistory, UUID> {

    List<OrderStatusHistory> findByOrder(Order order);
}
