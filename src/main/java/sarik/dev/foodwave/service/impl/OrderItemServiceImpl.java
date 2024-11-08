package sarik.dev.foodwave.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sarik.dev.foodwave.dto.order.item.OrderItemCreateDTO;
import sarik.dev.foodwave.dto.order.item.OrderItemDTO;
import sarik.dev.foodwave.dto.order.item.OrderItemUpdateDTO;
import sarik.dev.foodwave.entity.user.Dish;
import sarik.dev.foodwave.entity.user.Order;
import sarik.dev.foodwave.entity.user.OrderItem;
import sarik.dev.foodwave.repository.DishRepository;
import sarik.dev.foodwave.repository.OrderItemRepository;
import sarik.dev.foodwave.repository.OrderRepository;
import sarik.dev.foodwave.service.OrderItemService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {

    private final OrderItemRepository orderItemRepository;
    private final OrderRepository orderRepository;
    private final DishRepository dishRepository;

    @Override
    @Transactional
    public OrderItemDTO addOrderItem(OrderItemCreateDTO orderItemCreateDTO) {
        Order order = orderRepository.findById(orderItemCreateDTO.getOrderId())
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));

        Dish dish = dishRepository.findById(orderItemCreateDTO.getDishId())
                .orElseThrow(() -> new IllegalArgumentException("Dish not found"));

        OrderItem orderItem = OrderItem.builder()
                .order(order)
                .dish(dish)
                .quantity(orderItemCreateDTO.getQuantity())
                .build();

        orderItem.calculateTotalPrice();
        orderItemRepository.save(orderItem);

        // Recalculate total price for the order
        order.recalculateTotalPrice();
        orderRepository.save(order);

        return OrderItemDTO.fromEntity(orderItem);
    }

    @Override
    public OrderItemDTO getOrderItemById(UUID orderItemId) {
        OrderItem orderItem = orderItemRepository.findById(orderItemId)
                .orElseThrow(() -> new IllegalArgumentException("Order item not found"));
        return OrderItemDTO.fromEntity(orderItem);
    }

    @Override
    public List<OrderItemDTO> getOrderItemsByOrderId(UUID orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));

        return orderItemRepository.findByOrder(order).stream()
                .map(OrderItemDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public OrderItemDTO updateOrderItem(UUID orderItemId, OrderItemUpdateDTO orderItemUpdateDTO) {
        OrderItem orderItem = orderItemRepository.findById(orderItemId)
                .orElseThrow(() -> new IllegalArgumentException("Order item not found"));

        orderItem.setQuantity(orderItemUpdateDTO.getQuantity());
        orderItem.calculateTotalPrice();
        orderItemRepository.save(orderItem);

        // Recalculate total price for the order
        orderItem.getOrder().recalculateTotalPrice();
        orderRepository.save(orderItem.getOrder());

        return OrderItemDTO.fromEntity(orderItem);
    }

    @Override
    @Transactional
    public void deleteOrderItem(UUID orderItemId) {
        OrderItem orderItem = orderItemRepository.findById(orderItemId)
                .orElseThrow(() -> new IllegalArgumentException("Order item not found"));

        Order order = orderItem.getOrder();
        order.removeItem(orderItem);
        orderItemRepository.delete(orderItem);

        // Recalculate total price for the order
        order.recalculateTotalPrice();
        orderRepository.save(order);
    }
}
