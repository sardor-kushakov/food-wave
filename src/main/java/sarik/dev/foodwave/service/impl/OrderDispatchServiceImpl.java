package sarik.dev.foodwave.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sarik.dev.foodwave.dto.order.dispatch.OrderDispatchCreateDTO;
import sarik.dev.foodwave.dto.order.dispatch.OrderDispatchDTO;
import sarik.dev.foodwave.dto.order.dispatch.OrderDispatchUpdateDTO;
import sarik.dev.foodwave.entity.admin.OrderDispatch;
import sarik.dev.foodwave.entity.user.Order;
import sarik.dev.foodwave.enums.admin.DispatchStatus;
import sarik.dev.foodwave.repository.OrderDispatchRepository;
import sarik.dev.foodwave.repository.OrderRepository;
import sarik.dev.foodwave.service.OrderDispatchService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderDispatchServiceImpl implements OrderDispatchService {

    private final OrderDispatchRepository orderDispatchRepository;
    private final OrderRepository orderRepository;

    @Override
    @Transactional
    public OrderDispatchDTO createOrderDispatch(OrderDispatchCreateDTO orderDispatchCreateDTO) {
        Order order = orderRepository.findById(orderDispatchCreateDTO.getOrderId())
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));

        OrderDispatch orderDispatch = OrderDispatch.builder()
                .order(order)
                .courierName(orderDispatchCreateDTO.getCourierName())
                .courierContact(orderDispatchCreateDTO.getCourierContact())
                .dispatchStatus(orderDispatchCreateDTO.getDispatchStatus())
                .estimatedDeliveryTime(orderDispatchCreateDTO.getEstimatedDeliveryTime())
                .build();

        orderDispatchRepository.save(orderDispatch);
        return OrderDispatchDTO.fromEntity(orderDispatch);
    }

    @Override
    public OrderDispatchDTO getOrderDispatchById(UUID dispatchId) {
        OrderDispatch orderDispatch = orderDispatchRepository.findById(dispatchId)
                .orElseThrow(() -> new IllegalArgumentException("Order dispatch not found"));
        return OrderDispatchDTO.fromEntity(orderDispatch);
    }

    @Override
    public List<OrderDispatchDTO> getOrderDispatchesByStatus(DispatchStatus status) {
        return orderDispatchRepository.findByDispatchStatus(status).stream()
                .map(OrderDispatchDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public OrderDispatchDTO updateOrderDispatch(UUID dispatchId, OrderDispatchUpdateDTO orderDispatchUpdateDTO) {
        OrderDispatch orderDispatch = orderDispatchRepository.findById(dispatchId)
                .orElseThrow(() -> new IllegalArgumentException("Order dispatch not found"));

        orderDispatch.setCourierName(orderDispatchUpdateDTO.getCourierName());
        orderDispatch.setCourierContact(orderDispatchUpdateDTO.getCourierContact());
        orderDispatch.setDispatchStatus(orderDispatchUpdateDTO.getDispatchStatus());
        orderDispatch.setEstimatedDeliveryTime(orderDispatchUpdateDTO.getEstimatedDeliveryTime());

        orderDispatchRepository.save(orderDispatch);
        return OrderDispatchDTO.fromEntity(orderDispatch);
    }

    @Override
    @Transactional
    public void updateDispatchStatus(UUID dispatchId, DispatchStatus newStatus) {
        OrderDispatch orderDispatch = orderDispatchRepository.findById(dispatchId)
                .orElseThrow(() -> new IllegalArgumentException("Order dispatch not found"));

        orderDispatch.updateStatus(newStatus);
        orderDispatchRepository.save(orderDispatch);
    }

    @Override
    @Transactional
    public void deleteOrderDispatch(UUID dispatchId) {

    }
}