package sarik.dev.foodwave.entitiy.admin;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "coupons")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @NotBlank
    @Size(max = 20)
    @Column(name = "code", nullable = false, unique = true)
    private String code;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    @Column(name = "discount_amount", precision = 10, scale = 2, nullable = false)
    private BigDecimal discountAmount;

    @Min(0)
    @Max(100)
    @Column(name = "discount_percentage")
    private Integer discountPercentage;

    @NotNull
    @Column(name = "is_active", nullable = false)
    private boolean isActive = true;

    @NotNull
    @Column(name = "valid_from", nullable = false)
    private LocalDateTime validFrom;

    @NotNull
    @Column(name = "valid_until", nullable = false)
    private LocalDateTime validUntil;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // Methods

    /**
     * Checks if the coupon is currently valid.
     */
    public boolean isValid() {
        LocalDateTime now = LocalDateTime.now();
        return isActive && now.isAfter(validFrom) && now.isBefore(validUntil);
    }

    /**
     * Activates the coupon.
     */
    public void activate() {
        this.isActive = true;
    }

    /**
     * Deactivates the coupon.
     */
    public void deactivate() {
        this.isActive = false;
    }
}
