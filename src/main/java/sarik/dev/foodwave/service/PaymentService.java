package sarik.dev.foodwave.service;

import sarik.dev.foodwave.dto.payment.PaymentCreateDTO;
import sarik.dev.foodwave.dto.payment.PaymentDTO;
import sarik.dev.foodwave.enums.user.PaymentStatus;

import java.util.List;
import java.util.UUID;

public interface PaymentService {

    PaymentDTO createPayment(PaymentCreateDTO paymentCreateDTO);

    PaymentDTO getPaymentById(UUID paymentId);

    List<PaymentDTO> getPaymentsByOrderId(UUID orderId);

    List<PaymentDTO> getPaymentsByStatus(PaymentStatus status);

    PaymentDTO updatePaymentStatus(UUID paymentId, PaymentStatus newStatus);

    void deletePayment(UUID paymentId);
}
