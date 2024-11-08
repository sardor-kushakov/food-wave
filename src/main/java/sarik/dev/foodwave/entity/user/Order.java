package sarik.dev.foodwave.entity.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import sarik.dev.foodwave.enums.user.OrderStatus;
import sarik.dev.foodwave.enums.user.PaymentMethod;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@SQLDelete(sql = "UPDATE orders SET is_deleted = true WHERE id = ?")
@Filter(name = "deletedFilter", condition = "is_deleted = :isDeleted")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<OrderItem> items = new HashSet<>();

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    @Column(name = "total_price", nullable = false)
    private BigDecimal totalPrice;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private OrderStatus status;

    @NotNull
    @Column(name = "payment_method", nullable = false)
    private PaymentMethod paymentMethod;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // Methods

    /**
     * Adds an item to the order.
     */
    public void addItem(OrderItem item) {
        items.add(item);
        item.setOrder(this);
        recalculateTotalPrice();
    }

    /**
     * Removes an item from the order.
     */
    public void removeItem(OrderItem item) {
        items.remove(item);
        item.setOrder(null); // Olishni to'g'rilash
        recalculateTotalPrice();
    }

    /**
     * Recalculates the total price of all items in the order.
     */
    public void recalculateTotalPrice() {
        totalPrice = items.stream()
                .map(OrderItem::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    /**
     * Updates the status of the order.
     */
    public void updateStatus(OrderStatus newStatus) {
        this.status = newStatus;
    }
}
