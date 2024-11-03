package sarik.dev.foodwave.dto.order.history;

import lombok.Data;
import sarik.dev.foodwave.dto.order.OrderDTO;
import sarik.dev.foodwave.dto.user.UserDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class OrderHistoryDTO {

    private UUID id;
    private UserDTO user;
    private OrderDTO order;
    private BigDecimal totalPrice;
    private String status;
    private LocalDateTime orderDate;
}
