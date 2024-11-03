package sarik.dev.foodwave.dto.coupon;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
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
}
