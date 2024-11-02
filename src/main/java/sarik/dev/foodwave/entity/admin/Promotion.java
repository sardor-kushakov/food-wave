package sarik.dev.foodwave.entity.admin;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "promotions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Promotion {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank
    @Size(max = 100)
    @Column(nullable = false)
    private String name;

    @Lob
    private String description;

    @Min(0)
    @Max(100)
    @Column(name = "discount_percentage", nullable = false)
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
     * Checks if the promotion is currently valid.
     */
    public boolean isValid() {
        LocalDateTime now = LocalDateTime.now();
        return isActive && now.isAfter(validFrom) && now.isBefore(validUntil);
    }

    /**
     * Activates the promotion.
     */
    public void activate() {
        this.isActive = true;
    }

    /**
     * Deactivates the promotion.
     */
    public void deactivate() {
        this.isActive = false;
    }
}
