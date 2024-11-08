package sarik.dev.foodwave.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sarik.dev.foodwave.entity.user.Payment;
import sarik.dev.foodwave.entity.user.Order;
import sarik.dev.foodwave.enums.user.PaymentStatus;

import java.util.List;
import java.util.UUID;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, UUID> {

    List<Payment> findByOrder(Order order);

    List<Payment> findByStatus(PaymentStatus status);
}
