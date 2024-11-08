package sarik.dev.foodwave.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sarik.dev.foodwave.entity.user.Transaction;
import sarik.dev.foodwave.entity.user.Payment;
import sarik.dev.foodwave.enums.user.TransactionStatus;
import sarik.dev.foodwave.enums.user.TransactionType;

import java.util.List;
import java.util.UUID;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, UUID> {

    List<Transaction> findByPayment(Payment payment);

    List<Transaction> findByTransactionType(TransactionType transactionType);

    List<Transaction> findByStatus(TransactionStatus status);

    List<Transaction> findByReferenceNumber(String referenceNumber);
}
