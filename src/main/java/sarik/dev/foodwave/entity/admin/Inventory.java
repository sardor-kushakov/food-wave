package sarik.dev.foodwave.entity.admin;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "inventory")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank
    @Size(max = 100)
    @Column(name = "ingredient_name", nullable = false)
    private String ingredientName;

    @Min(0)
    @Column(nullable = false)
    private double quantity;

    @NotBlank
    @Size(max = 20)
    @Column(nullable = false)
    private String unit; // Example: "kg", "liters", "pieces"

    @CreationTimestamp
    @Column(name = "added_at", updatable = false)
    private LocalDateTime addedAt;

    @UpdateTimestamp
    @Column(name = "last_updated")
    private LocalDateTime lastUpdated;

    // Relationships

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    // Methods

    /**
     * Updates the quantity of the ingredient in the inventory.
     */
    public void updateQuantity(double amount) {
        this.quantity += amount;
    }

    /**
     * Checks if there is enough quantity of the ingredient.
     */
    public boolean hasSufficientQuantity(double requiredAmount) {
        return this.quantity >= requiredAmount;
    }
}
