package sarik.dev.foodwave.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sarik.dev.foodwave.entity.admin.OrderDispatch;
import sarik.dev.foodwave.entity.user.Order;
import sarik.dev.foodwave.enums.admin.DispatchStatus;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrderDispatchRepository extends JpaRepository<OrderDispatch, UUID> {

    Optional<OrderDispatch> findByOrder(Order order);

    List<OrderDispatch> findByDispatchStatus(DispatchStatus status);
}
