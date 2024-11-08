package sarik.dev.foodwave.service;

import sarik.dev.foodwave.dto.order.OrderCreateDTO;
import sarik.dev.foodwave.dto.order.OrderDTO;
import sarik.dev.foodwave.enums.user.OrderStatus;

import java.util.List;
import java.util.UUID;

public interface OrderService {

    OrderDTO createOrder(OrderCreateDTO orderCreateDTO);

    OrderDTO getOrderById(UUID orderId);

    List<OrderDTO> getOrdersByUser(UUID userId);

    List<OrderDTO> getOrdersByStatus(OrderStatus status);

    OrderDTO updateOrderStatus(UUID orderId, OrderStatus newStatus);

    void deleteOrder(UUID orderId);
}
