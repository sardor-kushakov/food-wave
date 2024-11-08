package sarik.dev.foodwave.entity.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "order_items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "dish_id", nullable = false)
    private Dish dish;

    @NotNull
    @Min(1)
    @Column(nullable = false)
    private Integer quantity;

    @Column(name = "total_price", precision = 10, scale = 2)
    private BigDecimal totalPrice;

    // Methods

    /**
     * Calculates the total price based on quantity and dish price.
     */
    public void calculateTotalPrice() {
        if (dish != null) {
            this.totalPrice = dish.getPrice().multiply(BigDecimal.valueOf(quantity));
        } else {
            this.totalPrice = BigDecimal.ZERO; // Agar dish mavjud bo'lmasa, 0 qilib qo'yamiz
        }
    }

    /**
     * Gets the total price of the order item.
     */
    public BigDecimal getTotalPrice() {
        if (totalPrice == null) {
            calculateTotalPrice(); // Agar narx hali hisoblanmagan bo'lsa, hisoblash
        }
        return totalPrice;
    }
}
