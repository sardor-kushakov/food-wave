package sarik.dev.foodwave.dto.coupon;

import lombok.Builder;
import lombok.Data;
import sarik.dev.foodwave.entity.admin.Coupon;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class CouponDTO {

    private UUID id;
    private String code;
    private BigDecimal discountAmount;
    private Integer discountPercentage;
    private boolean isActive;
    private LocalDateTime validFrom;
    private LocalDateTime validUntil;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    /**
     * Converts a Coupon entity to a CouponDTO.
     *
     * @param coupon the Coupon entity
     * @return a CouponDTO with data from the entity
     */
    public static CouponDTO fromEntity(Coupon coupon) {
        return CouponDTO.builder()
                .id(coupon.getId())
                .code(coupon.getCode())
                .discountAmount(coupon.getDiscountAmount())
                .discountPercentage(coupon.getDiscountPercentage())
                .isActive(coupon.isActive())
                .validFrom(coupon.getValidFrom())
                .validUntil(coupon.getValidUntil())
                .createdAt(coupon.getCreatedAt())
                .updatedAt(coupon.getUpdatedAt())
                .build();
    }
}
