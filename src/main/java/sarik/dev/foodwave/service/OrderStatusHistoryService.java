package sarik.dev.foodwave.service;

import sarik.dev.foodwave.dto.order.status.OrderStatusHistoryCreateDTO;
import sarik.dev.foodwave.dto.order.status.OrderStatusHistoryDTO;

import java.util.List;
import java.util.UUID;

public interface OrderStatusHistoryService {

    OrderStatusHistoryDTO createOrderStatusHistory(OrderStatusHistoryCreateDTO orderStatusHistoryCreateDTO);

    OrderStatusHistoryDTO getOrderStatusHistoryById(UUID orderStatusHistoryId);

    List<OrderStatusHistoryDTO> getOrderStatusHistoryByOrderId(UUID orderId);

    List<OrderStatusHistoryDTO> getAllOrderStatusHistories();
}
