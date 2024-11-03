package sarik.dev.foodwave.dto.inventory;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.UUID;

@Data
public class InventoryUpdateDTO {

    private UUID id;

    @NotBlank
    @Size(max = 100)
    private String inventoryName;

    @Min(0)
    private double quantity;

    @NotBlank
    @Size(max = 20)
    private String unit;

    private UUID restaurantId;
}
