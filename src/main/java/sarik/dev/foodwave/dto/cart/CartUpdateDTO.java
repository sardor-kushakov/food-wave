package sarik.dev.foodwave.dto.cart;

import lombok.Data;

import java.util.UUID;
import java.math.BigDecimal;

@Data
public class CartUpdateDTO {

    private UUID id;
    private boolean isCheckedOut;
    private BigDecimal totalPrice;
}
