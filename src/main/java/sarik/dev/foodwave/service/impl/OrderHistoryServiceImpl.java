package sarik.dev.foodwave.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sarik.dev.foodwave.dto.order.history.OrderHistoryCreateDTO;
import sarik.dev.foodwave.dto.order.history.OrderHistoryDTO;
import sarik.dev.foodwave.dto.order.history.OrderHistoryUpdateDTO;
import sarik.dev.foodwave.entity.admin.OrderHistory;
import sarik.dev.foodwave.entity.user.Order;
import sarik.dev.foodwave.entity.user.User;
import sarik.dev.foodwave.repository.OrderHistoryRepository;
import sarik.dev.foodwave.repository.OrderRepository;
import sarik.dev.foodwave.repository.UserRepository;
import sarik.dev.foodwave.service.OrderHistoryService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderHistoryServiceImpl implements OrderHistoryService {

    private final OrderHistoryRepository orderHistoryRepository;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;

    @Override
    @Transactional
    public OrderHistoryDTO createOrderHistory(OrderHistoryCreateDTO orderHistoryCreateDTO) {
        User user = userRepository.findById(orderHistoryCreateDTO.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Order order = orderRepository.findById(orderHistoryCreateDTO.getOrderId())
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));

        OrderHistory orderHistory = OrderHistory.builder()
                .user(user)
                .order(order)
                .totalPrice(orderHistoryCreateDTO.getTotalPrice())
                .status(orderHistoryCreateDTO.getStatus())
                .build();

        orderHistoryRepository.save(orderHistory);
        return OrderHistoryDTO.fromEntity(orderHistory);
    }

    @Override
    public OrderHistoryDTO getOrderHistoryById(UUID historyId) {
        OrderHistory orderHistory = orderHistoryRepository.findById(historyId)
                .orElseThrow(() -> new IllegalArgumentException("Order history not found"));
        return OrderHistoryDTO.fromEntity(orderHistory);
    }

    @Override
    public List<OrderHistoryDTO> getOrderHistoryByUser(UUID userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        return orderHistoryRepository.findByUser(user).stream()
                .map(OrderHistoryDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public OrderHistoryDTO updateOrderHistory(UUID historyId, OrderHistoryUpdateDTO orderHistoryUpdateDTO) {
        OrderHistory orderHistory = orderHistoryRepository.findById(historyId)
                .orElseThrow(() -> new IllegalArgumentException("Order history not found"));

        orderHistory.setTotalPrice(orderHistoryUpdateDTO.getTotalPrice());
        orderHistory.setStatus(orderHistoryUpdateDTO.getStatus());

        orderHistoryRepository.save(orderHistory);
        return OrderHistoryDTO.fromEntity(orderHistory);
    }

    @Override
    @Transactional
    public void deleteOrderHistory(UUID historyId) {
        if (orderHistoryRepository.existsById(historyId)) {
            orderHistoryRepository.deleteById(historyId);
        } else {
            throw new IllegalArgumentException("Order history not found");
        }
    }

    @Override
    public String getOrderSummary(UUID historyId) {
        OrderHistory orderHistory = orderHistoryRepository.findById(historyId)
                .orElseThrow(() -> new IllegalArgumentException("Order history not found"));
        return orderHistory.getOrderSummary();
    }
}
