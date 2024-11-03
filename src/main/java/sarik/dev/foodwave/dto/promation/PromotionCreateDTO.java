package sarik.dev.foodwave.dto.promation;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PromotionCreateDTO {

    @NotBlank
    @Size(max = 100)
    private String name;

    private String description;

    @Min(0)
    @Max(100)
    private Integer discountPercentage;

    @NotNull
    private LocalDateTime validFrom;

    @NotNull
    private LocalDateTime validUntil;
}
