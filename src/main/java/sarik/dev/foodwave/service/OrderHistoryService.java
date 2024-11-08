package sarik.dev.foodwave.service;

import sarik.dev.foodwave.dto.order.history.OrderHistoryCreateDTO;
import sarik.dev.foodwave.dto.order.history.OrderHistoryDTO;
import sarik.dev.foodwave.dto.order.history.OrderHistoryUpdateDTO;

import java.util.List;
import java.util.UUID;

public interface OrderHistoryService {

    OrderHistoryDTO createOrderHistory(OrderHistoryCreateDTO orderHistoryCreateDTO);

    OrderHistoryDTO getOrderHistoryById(UUID historyId);

    List<OrderHistoryDTO> getOrderHistoryByUser(UUID userId);

    OrderHistoryDTO updateOrderHistory(UUID historyId, OrderHistoryUpdateDTO orderHistoryUpdateDTO);

    void deleteOrderHistory(UUID historyId);

    String getOrderSummary(UUID historyId);
}
