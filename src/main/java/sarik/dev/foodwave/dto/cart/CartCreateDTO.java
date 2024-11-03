package sarik.dev.foodwave.dto.cart;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class CartCreateDTO {

    @NotNull
    private UUID userId;
}
