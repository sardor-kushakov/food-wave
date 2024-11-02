package sarik.dev.foodwave.entitiy.admin;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import sarik.dev.foodwave.entitiy.user.Order;
import sarik.dev.foodwave.enums.admin.DispatchStatus;

import java.time.LocalDateTime;

@Entity
@Table(name = "order_dispatch")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDispatch {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @NotBlank
    @Column(name = "courier_name", nullable = false)
    private String courierName;

    @NotBlank
    @Column(name = "courier_contact", nullable = false)
    private String courierContact;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "dispatch_status", nullable = false)
    private DispatchStatus dispatchStatus;

    @Column(name = "estimated_delivery_time")
    private LocalDateTime estimatedDeliveryTime;

    @CreationTimestamp
    @Column(name = "dispatched_at", updatable = false)
    private LocalDateTime dispatchedAt;

    @UpdateTimestamp
    @Column(name = "last_updated")
    private LocalDateTime lastUpdated;

    // Methods

    /**
     * Updates the status of the order dispatch.
     */
    public void updateStatus(DispatchStatus newStatus) {
        this.dispatchStatus = newStatus;
    }
}
