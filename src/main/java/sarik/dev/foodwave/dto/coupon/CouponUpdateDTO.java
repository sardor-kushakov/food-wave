package sarik.dev.foodwave.dto.coupon;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class CouponUpdateDTO {

    private UUID id;

    @NotBlank
    @Size(max = 20)
    private String code;

    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal discountAmount;

    @Min(0)
    @Max(100)
    private Integer discountPercentage;

    private boolean isActive;
    private LocalDateTime validFrom;
    private LocalDateTime validUntil;
}
