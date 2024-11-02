package sarik.dev.foodwave.entity.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import sarik.dev.foodwave.entity.BaseEntity;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "addresses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@SQLDelete(sql = "UPDATE addresses SET is_deleted = true WHERE id = ?")
@Filter(name = "deletedFilter", condition = "is_deleted = :isDeleted")
public class Address extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @NotBlank
    @Size(max = 100)
    @Column(name = "address_line_1", nullable = false)
    private String addressLine1;

    @Size(max = 100)
    @Column(name = "address_line_2")
    private String addressLine2;

    @NotBlank
    @Size(max = 50)
    private String city;

    @NotBlank
    @Size(max = 50)
    private String state;

    @NotBlank
    @Size(max = 20)
    private String zipCode;

    @NotBlank
    @Size(max = 50)
    private String country;

    @Column(name = "is_primary", nullable = false)
    private boolean isPrimary = false;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // Optional fields for geolocation
    @Pattern(regexp = "^-?\\d{1,3}\\.\\d+$", message = "Invalid latitude format")
    @Column(name = "latitude")
    private String latitude;

    @Pattern(regexp = "^-?\\d{1,3}\\.\\d+$", message = "Invalid longitude format")
    @Column(name = "longitude")
    private String longitude;

    // Additional Methods

    /**
     * Mark this address as the primary address.
     */
    public void setAsPrimary() {
        this.isPrimary = true;
    }
}
