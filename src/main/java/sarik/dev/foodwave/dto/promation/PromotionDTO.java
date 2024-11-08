package sarik.dev.foodwave.dto.promation;

import lombok.Builder;
import lombok.Data;
import sarik.dev.foodwave.entity.admin.Promotion;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
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

    /**
     * Converts a Promotion entity to a PromotionDTO.
     *
     * @param promotion the Promotion entity
     * @return a PromotionDTO with data from the entity
     */
    public static PromotionDTO fromEntity(Promotion promotion) {
        return PromotionDTO.builder()
                .id(promotion.getId())
                .name(promotion.getName())
                .description(promotion.getDescription())
                .discountPercentage(promotion.getDiscountPercentage())
                .isActive(promotion.isActive())
                .validFrom(promotion.getValidFrom())
                .validUntil(promotion.getValidUntil())
                .createdAt(promotion.getCreatedAt())
                .updatedAt(promotion.getUpdatedAt())
                .build();
    }
}
