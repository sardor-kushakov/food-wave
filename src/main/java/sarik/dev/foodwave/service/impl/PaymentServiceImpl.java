package sarik.dev.foodwave.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sarik.dev.foodwave.dto.payment.PaymentCreateDTO;
import sarik.dev.foodwave.dto.payment.PaymentDTO;
import sarik.dev.foodwave.entity.user.Payment;
import sarik.dev.foodwave.entity.user.Order;
import sarik.dev.foodwave.enums.user.PaymentStatus;
import sarik.dev.foodwave.repository.PaymentRepository;
import sarik.dev.foodwave.repository.OrderRepository;
import sarik.dev.foodwave.service.PaymentService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;

    @Override
    @Transactional
    public PaymentDTO createPayment(PaymentCreateDTO paymentCreateDTO) {
        Order order = orderRepository.findById(paymentCreateDTO.getOrderId())
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));

        Payment payment = Payment.builder()
                .order(order)
                .paymentMethod(paymentCreateDTO.getPaymentMethod())
                .amount(paymentCreateDTO.getAmount())
                .status(PaymentStatus.PENDING)
                .build();

        paymentRepository.save(payment);
        return PaymentDTO.fromEntity(payment);
    }

    @Override
    public PaymentDTO getPaymentById(UUID paymentId) {
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new IllegalArgumentException("Payment not found"));
        return PaymentDTO.fromEntity(payment);
    }

    @Override
    public List<PaymentDTO> getPaymentsByOrderId(UUID orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));

        return paymentRepository.findByOrder(order).stream()
                .map(PaymentDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<PaymentDTO> getPaymentsByStatus(PaymentStatus status) {
        return paymentRepository.findByStatus(status).stream()
                .map(PaymentDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public PaymentDTO updatePaymentStatus(UUID paymentId, PaymentStatus newStatus) {
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new IllegalArgumentException("Payment not found"));

        payment.updateStatus(newStatus);
        paymentRepository.save(payment);
        return PaymentDTO.fromEntity(payment);
    }

    @Override
    @Transactional
    public void deletePayment(UUID paymentId) {
        if (paymentRepository.existsById(paymentId)) {
            paymentRepository.deleteById(paymentId);
        } else {
            throw new IllegalArgumentException("Payment not found");
        }
    }
}
