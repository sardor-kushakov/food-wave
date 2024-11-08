package sarik.dev.foodwave.entity.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "cart_items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cart_id", nullable = false)
    private Cart cart;

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
     * Gets the product (dish) associated with this cart item.
     */
    public Dish getProduct() {
        return this.dish;
    }

    /**
     * Calculates the total price based on quantity and dish price.
     */
    public void calculateTotalPrice() {
        if (dish != null) {
            this.totalPrice = dish.getPrice().multiply(BigDecimal.valueOf(quantity));
        } else {
            this.totalPrice = BigDecimal.ZERO; // Agar dish mavjud bo'lmasa, 0 qilib qo'ying
        }
    }
}
