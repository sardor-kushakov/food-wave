package sarik.dev.foodwave.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sarik.dev.foodwave.dto.order.status.OrderStatusHistoryCreateDTO;
import sarik.dev.foodwave.dto.order.status.OrderStatusHistoryDTO;
import sarik.dev.foodwave.entity.user.Order;
import sarik.dev.foodwave.entity.user.OrderStatusHistory;
import sarik.dev.foodwave.repository.OrderRepository;
import sarik.dev.foodwave.repository.OrderStatusHistoryRepository;
import sarik.dev.foodwave.service.OrderStatusHistoryService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderStatusHistoryServiceImpl implements OrderStatusHistoryService {

    private final OrderStatusHistoryRepository orderStatusHistoryRepository;
    private final OrderRepository orderRepository;

    @Override
    @Transactional
    public OrderStatusHistoryDTO createOrderStatusHistory(OrderStatusHistoryCreateDTO orderStatusHistoryCreateDTO) {
        Order order = orderRepository.findById(orderStatusHistoryCreateDTO.getOrderId())
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));

        OrderStatusHistory orderStatusHistory = OrderStatusHistory.builder()
                .order(order)
                .status(orderStatusHistoryCreateDTO.getStatus())
                .changedBy(orderStatusHistoryCreateDTO.getChangedBy())
                .build();

        orderStatusHistoryRepository.save(orderStatusHistory);
        return OrderStatusHistoryDTO.fromEntity(orderStatusHistory);
    }

    @Override
    public OrderStatusHistoryDTO getOrderStatusHistoryById(UUID orderStatusHistoryId) {
        OrderStatusHistory orderStatusHistory = orderStatusHistoryRepository.findById(orderStatusHistoryId)
                .orElseThrow(() -> new IllegalArgumentException("Order status history not found"));
        return OrderStatusHistoryDTO.fromEntity(orderStatusHistory);
    }

    @Override
    public List<OrderStatusHistoryDTO> getOrderStatusHistoryByOrderId(UUID orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));

        return orderStatusHistoryRepository.findByOrder(order).stream()
                .map(OrderStatusHistoryDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderStatusHistoryDTO> getAllOrderStatusHistories() {
        return orderStatusHistoryRepository.findAll().stream()
                .map(OrderStatusHistoryDTO::fromEntity)
                .collect(Collectors.toList());
    }
}
