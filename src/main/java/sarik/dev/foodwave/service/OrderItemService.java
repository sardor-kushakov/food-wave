package sarik.dev.foodwave.service;

import sarik.dev.foodwave.dto.order.item.OrderItemCreateDTO;
import sarik.dev.foodwave.dto.order.item.OrderItemDTO;
import sarik.dev.foodwave.dto.order.item.OrderItemUpdateDTO;

import java.util.List;
import java.util.UUID;

public interface OrderItemService {

    OrderItemDTO addOrderItem(OrderItemCreateDTO orderItemCreateDTO);

    OrderItemDTO getOrderItemById(UUID orderItemId);

    List<OrderItemDTO> getOrderItemsByOrderId(UUID orderId);

    OrderItemDTO updateOrderItem(UUID orderItemId, OrderItemUpdateDTO orderItemUpdateDTO);

    void deleteOrderItem(UUID orderItemId);
}
