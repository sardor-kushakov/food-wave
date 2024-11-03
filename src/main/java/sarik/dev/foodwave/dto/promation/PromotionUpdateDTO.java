package sarik.dev.foodwave.dto.promation;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class PromotionUpdateDTO {

    private UUID id;

    @NotBlank
    @Size(max = 100)
    private String name;

    private String description;

    @Min(0)
    @Max(100)
    private Integer discountPercentage;

    private boolean isActive;

    @NotNull
    private LocalDateTime validFrom;

    @NotNull
    private LocalDateTime validUntil;
}
