package sarik.dev.foodwave.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sarik.dev.foodwave.dto.order.OrderCreateDTO;
import sarik.dev.foodwave.dto.order.OrderDTO;
import sarik.dev.foodwave.dto.order.item.OrderItemCreateDTO;
import sarik.dev.foodwave.entity.user.Order;
import sarik.dev.foodwave.entity.user.OrderItem;
import sarik.dev.foodwave.entity.user.User;
import sarik.dev.foodwave.enums.user.OrderStatus;
import sarik.dev.foodwave.repository.DishRepository;
import sarik.dev.foodwave.repository.OrderRepository;
import sarik.dev.foodwave.repository.UserRepository;
import sarik.dev.foodwave.service.OrderService;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final DishRepository dishRepository;

    @Override
    @Transactional
    public OrderDTO createOrder(OrderCreateDTO orderCreateDTO) {
        User user = userRepository.findById(orderCreateDTO.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Order order = Order.builder()
                .user(user)
                .status(OrderStatus.PENDING)
                .paymentMethod(orderCreateDTO.getPaymentMethod())
                .totalPrice(calculateTotalPrice(orderCreateDTO.getItems())) // Items ni olish
                .build();

        orderRepository.save(order);
        return OrderDTO.fromEntity(order);
    }

    private BigDecimal calculateTotalPrice(List<OrderItemCreateDTO> orderItemCreateDTOs) {
        return orderItemCreateDTOs.stream()
                .map(orderItemCreateDTO -> {
                    // Buyurtma elementini yaratish
                    OrderItem orderItem = OrderItem.builder()
                            .dish(dishRepository.findById(orderItemCreateDTO.getDishId())
                                    .orElseThrow(() -> new IllegalArgumentException("Dish not found")))
                            .quantity(orderItemCreateDTO.getQuantity())
                            .build();

                    // Narxni hisoblash
                    orderItem.calculateTotalPrice(); // Bu yerda hisoblangan totalPrice ni olasiz
                    return orderItem.getTotalPrice(); // Total price ni qaytaradi
                })
                .reduce(BigDecimal.ZERO, BigDecimal::add); // Jami total price ni hisoblash
    }


    @Override
    public OrderDTO getOrderById(UUID orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));
        return OrderDTO.fromEntity(order);
    }

    @Override
    public List<OrderDTO> getOrdersByUser(UUID userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        return orderRepository.findByUser(user).stream()
                .map(OrderDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderDTO> getOrdersByStatus(OrderStatus status) {
        return orderRepository.findByStatus(status).stream()
                .map(OrderDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public OrderDTO updateOrderStatus(UUID orderId, OrderStatus newStatus) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));

        order.updateStatus(newStatus);
        orderRepository.save(order);
        return OrderDTO.fromEntity(order);
    }

    @Override
    @Transactional
    public void deleteOrder(UUID orderId) {
        if (orderRepository.existsById(orderId)) {
            orderRepository.deleteById(orderId);
        } else {
            throw new IllegalArgumentException("Order not found");
        }
    }
}
