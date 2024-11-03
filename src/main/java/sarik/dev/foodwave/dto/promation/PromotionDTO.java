package sarik.dev.foodwave.dto.promation;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class PromotionDTO {

    private UUID id;
    private String name;
    private String description;
    private Integer discountPercentage;
    private boolean isActive;
    private LocalDateTime validFrom;
    private LocalDateTime validUntil;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
