package sarik.dev.foodwave.service;

import sarik.dev.foodwave.dto.order.dispatch.OrderDispatchCreateDTO;
import sarik.dev.foodwave.dto.order.dispatch.OrderDispatchDTO;
import sarik.dev.foodwave.dto.order.dispatch.OrderDispatchUpdateDTO;
import sarik.dev.foodwave.enums.admin.DispatchStatus;

import java.util.List;
import java.util.UUID;

public interface OrderDispatchService {

    OrderDispatchDTO createOrderDispatch(OrderDispatchCreateDTO orderDispatchCreateDTO);

    OrderDispatchDTO getOrderDispatchById(UUID dispatchId);

    List<OrderDispatchDTO> getOrderDispatchesByStatus(DispatchStatus status);

    OrderDispatchDTO updateOrderDispatch(UUID dispatchId, OrderDispatchUpdateDTO orderDispatchUpdateDTO);

    void updateDispatchStatus(UUID dispatchId, DispatchStatus newStatus);

    void deleteOrderDispatch(UUID dispatchId);
}
