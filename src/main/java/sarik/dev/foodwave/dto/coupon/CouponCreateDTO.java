package sarik.dev.foodwave.dto.coupon;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class CouponCreateDTO {

    @NotBlank
    @Size(max = 20)
    private String code;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal discountAmount;

    @Min(0)
    @Max(100)
    private Integer discountPercentage;

    @NotNull
    private LocalDateTime validFrom;

    @NotNull
    private LocalDateTime validUntil;
}
