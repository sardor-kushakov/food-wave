package sarik.dev.foodwave.entity.user;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import sarik.dev.foodwave.entity.BaseEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "carts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@SQLDelete(sql = "UPDATE carts SET is_deleted = true WHERE id = ?")
@Filter(name = "deletedFilter", condition = "is_deleted = :isDeleted")
public class Cart extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<CartItem> items = new HashSet<>();

    @Column(name = "is_checked_out", nullable = false)
    private boolean isCheckedOut = false;

    @Column(name = "total_price", precision = 10, scale = 2)
    private BigDecimal totalPrice;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // Methods

    /**
     * Adds an item to the cart.
     */
    public void addItem(CartItem item) {
        items.add(item);
        item.setCart(this);
        recalculateTotalPrice();
    }

    /**
     * Removes an item from the cart.
     */
    public void removeItem(CartItem item) {
        items.remove(item);
        item.setCart(null);
        recalculateTotalPrice();
    }

    /**
     * Recalculates the total price of all items in the cart.
     */
    public void recalculateTotalPrice() {
        totalPrice = items.stream()
                .map(CartItem::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    /**
     * Marks the cart as checked out.
     */
    public void checkout() {
        isCheckedOut = true;
    }
}
